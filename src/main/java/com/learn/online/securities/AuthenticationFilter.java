package com.learn.online.securities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import  org.springframework.security.core.AuthenticationException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.online.SpringApplicationContext;
import com.learn.online.requests.StudentLoginRequest;
import com.learn.online.services.StudentService;

import io.jsonwebtoken.Jwts;   //josn webtoken jjwt
import io.jsonwebtoken.SignatureAlgorithm; //josn webtoken jjwt

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	
	/*
	 * Login attempt
	 */
	@Override 
	public Authentication attemptAuthentication(HttpServletRequest request, 
				HttpServletResponse response) throws AuthenticationException {
		
		try {
		StudentLoginRequest studentLoginRequest = 
				new ObjectMapper()
					.readValue(request.getInputStream(), StudentLoginRequest.class);
		
		/*
		 * 	************************Login operation*****************8
		 * AuthenticationManager doing Authentication(Log-in). authenticate method accepts 
		 * incoming request username and password wrapped in instance of 
		 * UsernamePasswordAuthenticationToken as an argument. I injected this authentication
		 * manager by constructor in WebSecuirty during this filter creation in 
		 * overriding configure(HttpSecurity http) method of WebSecurity of sub type WebSecurityConfigurerAdapter. 
		 * In WebSecurity, I have have configured this authentication manager and 
		 * then I injected BCryptPasswordEncoder and StudentServiceImpl of sub type of UserDetailsService provided 
		 * by Spring security module. This authentication manager uses 
		 * StudentService.loadUserByUsername(String username)
		 * to fetch the credential from DB and after that Security mechanism uses ByCryptPasswordEncoder 
		 * to Encrypt and decrypt Password retrieved from DB. Remember Credeintail detials are loaded 
		 * in Spring provided sub type of UserDetails.
		 * If Authentication would pass successfully then security mechanism calls subsequent overriding 
		 * successfulAuthentication method of this same filter and then it generates JWT-WEBToken and 
		 * send it as Authorization response header value to http response and this is 
		 * this is how authentication completes and user can use this generated web token to 
		 * communicate with secured web resources. for that client has to pass the 
		 * this encrypted Authorization response web token value as value to request header 
		 * Authorization  of secured resources.   
		 */
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(studentLoginRequest.getEmail(), 
						studentLoginRequest.getPassword(), new ArrayList<>()));

		} catch(IOException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
	
	
	@Override
	public void successfulAuthentication(HttpServletRequest request, 
				HttpServletResponse response, FilterChain chain, Authentication auth) {
		
		
		/*
		 * Security frame work calls this overriding successfulAuthentication() method 
		 * after calling attemptAuthentication() method provided that preceding 
		 * attemptAuthentication() method call do successful authentication.
		 * This successfulAuthentication takes Authentication object as argument 
		 * Spring security mechanism retrieves this object as return value of 
		 * preceding attemptAuthentication() method call
		 * 
		 */
		
		String username = ((UserPrincipal)auth.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
			.setSubject(username)
			.setExpiration(new Date(System.currentTimeMillis() 
						+ SecurityConstants.EXPIRATION_TIME))
			.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
			.compact();
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);

		StudentService studentService = SpringApplicationContext
				.getBean("studentServiceImpl", StudentService.class);
	
		response.addHeader("userId", studentService.findByEmail(username).getStudentKey());
	}
	
}

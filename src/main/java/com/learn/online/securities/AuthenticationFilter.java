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
	
	
	@Override 
	public Authentication attemptAuthentication(HttpServletRequest request, 
				HttpServletResponse response) throws AuthenticationException {
		
		
		try {
		StudentLoginRequest studentLoginRequest = 
				new ObjectMapper()
					.readValue(request.getInputStream(), StudentLoginRequest.class);
		
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

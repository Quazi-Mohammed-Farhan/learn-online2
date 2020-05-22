package com.learn.online.securities;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.learn.online.SpringApplicationContext;
import com.learn.online.daos.StudentEntityDao;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		
		String rawTokenString = request.getHeader(SecurityConstants.HEADER_STRING);
		
		
		if(rawTokenString == null || !rawTokenString.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		} 
		
		String webToken = rawTokenString.replace(SecurityConstants.TOKEN_PREFIX, "");
	
		String username = Jwts.parser()
				.setSigningKey(SecurityConstants.TOKEN_SECRET)
				.parseClaimsJws(webToken)
				.getBody()
				.getSubject();
				
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
		
		if(username != null) {
			
			UserPrincipal userPrincipal = new UserPrincipal(SpringApplicationContext
					.getBean("studentEntityDao", StudentEntityDao.class)
					.findByEmail(username)
					.orElseGet(null));			
			
				usernamePasswordAuthenticationToken = 
					new UsernamePasswordAuthenticationToken(userPrincipal, null, 
							userPrincipal.getAuthorities());
			
			/*
			usernamePasswordAuthenticationToken = 
					new UsernamePasswordAuthenticationToken(username, null, 
							new UserPrincipal(SpringApplicationContext
									.getBean("studentEntityDao", StudentEntityDao.class)
									.findByEmail(username)
									.orElseGet(null)).getAuthorities());		
			*/		
		}
		
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		filterChain.doFilter(request, response);
		
	}
}

package com.learn.online.securities;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learn.online.services.StudentService;
import com.learn.online.utils.URLConstants;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private StudentService studentService;
	private BCryptPasswordEncoder  bCryptPasswordEncoder;
	
	public WebSecurity(StudentService studentService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		this.studentService = studentService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, SecurityConstants.SIGNUP_URL)
		.permitAll()
		.antMatchers(HttpMethod.POST, SecurityConstants.SIGNUP_URL)
		.permitAll()
		.antMatchers(HttpMethod.GET, URLConstants.SEARCH_COURSES_BY_DOMAIN_AND_RATING)
		.permitAll()
		.antMatchers(HttpMethod.GET, URLConstants.SEARCH_COURSES_BY_DOMAIN)
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new AuthenticationFilter(authenticationManager()))
		.addFilter(new AuthorizationFilter(authenticationManager()))
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(studentService).passwordEncoder(bCryptPasswordEncoder);
	}
	
}

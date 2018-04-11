package com.example.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers("/store")
		.authenticated()
		.antMatchers("/client")
		.authenticated()
	    .antMatchers("/share")
	    .authenticated()
		// Any other request must be authenticated
		.anyRequest().authenticated().and()
		// Custom filter for authenticating users using tokens
		.addFilterBefore(new JWTAuthenticationFilter(), BasicAuthenticationFilter.class)
		// Disable resource caching
		.headers().cacheControl();
	}
}

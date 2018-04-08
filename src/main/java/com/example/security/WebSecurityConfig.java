package com.example.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String ROLESTORE = "STORE";  
	private static final String ROLECUSTOMER = "CUSTOMER";  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/store").access("hasRole('ROLE_STORE')")
												 .antMatchers("/client").access("hasRole('ROLE_CUSTOMER')")
												 .antMatchers("/share").access("hasRole('ROLE_STORE')")
				                                 .antMatchers(HttpMethod.POST, "/login").permitAll()
				                                 .anyRequest().authenticated().and()
				// We filter the api/login requests
				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				// And filter other requests to check the presence of JWT in header
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("client").password("client").roles(ROLECUSTOMER)
		                       .and().withUser("store").password("store").roles(ROLESTORE)
		                       .and().withUser("share").password("share").roles(ROLESTORE);

	}
}

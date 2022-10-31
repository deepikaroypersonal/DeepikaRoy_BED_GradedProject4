package com.greatlearning.employees.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.employees.service.DomainUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter 
{

	private final DomainUserDetailsService userDetailsService;

	//authentication	
	@Bean
	public DaoAuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
		
	}
	//authentication
	@Bean
	public PasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() 
	{
		return userDetailsService;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception 
	{		
		authenticationManagerBuilder.authenticationProvider(authProvider());
	}
	
	//authorization
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception 
	{
		// configure authorization rules here
		httpSecurity.cors().disable();
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
		
		httpSecurity
		.authorizeRequests()
		.antMatchers(GET,"/swagger-ui**").permitAll()
		.antMatchers("/h2-console/**", "/login**", "/contact-us**").permitAll()
		.antMatchers(GET,"/api/employees/**").hasAnyAuthority("USER", "ADMIN")
		.and().authorizeRequests()
		.antMatchers(HttpMethod.POST,"/api/employees/**").hasAnyAuthority("ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/employees/**").hasAnyAuthority("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/employees/**").hasAnyAuthority("ADMIN")
		.anyRequest().authenticated()	
		.and().formLogin().and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(STATELESS);
	}
	
}

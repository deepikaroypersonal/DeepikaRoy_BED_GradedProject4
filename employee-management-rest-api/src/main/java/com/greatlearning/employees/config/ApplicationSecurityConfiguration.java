package com.greatlearning.employees.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
	public PasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DomainUserDetailsService userDetailsService() 
	{
		return userDetailsService;
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());

		return provider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception 
	{		
		authenticationManagerBuilder.authenticationProvider(authProvider());
	}

	
	//authorization
	@Override
	public void configure(WebSecurity web) throws Exception 
	{
		web.ignoring().antMatchers("/h2-console/**", "/configuration/ui", "/swagger-resources/**", "/configuration/**",
				"/swagger-ui**", "/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers("/login**").permitAll();
		http.authorizeRequests().antMatchers("/register/**").permitAll();

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/employees/**").hasAnyAuthority("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/employees/**").hasAnyAuthority("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/employees/**").hasAnyAuthority("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/employees/**").hasAnyAuthority("ADMIN")
		.and().httpBasic().and()
		.formLogin()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}

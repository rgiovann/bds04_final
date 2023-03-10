package com.devsuperior.bds04.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEnconder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	// need to inform wich algorithm to encrypt password (BCryptPasswordEncoder)
	// and inject UserDetailsService object
	// Spring Security on authenticating he knows how to search user and 
	// wich encoding is used to encrypt password
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEnconder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/actuator/**");  // pattern spring oauth2 uses to handle requests
	}

	@Override
	@Bean  // need to be available as component to my system (bean)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
		
}

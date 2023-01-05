package com.devsuperior.bds04.configuration;

 
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	private static final String[] PUBLIC = {"/oauth/token","/h2-console/**"};
	private static final String[] ALL_ENDPOINTS = {"/cities/**", "/events/**"};
	private static final String[] EVENTS = {"/events/**"};

	
	public ResourceServerConfig() {
	}

	// token configuration
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	// configure paths authorization
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		// needed for release of H2 page
		if( Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		 
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()
		.antMatchers(HttpMethod.GET,ALL_ENDPOINTS).permitAll()
		.antMatchers(HttpMethod.POST,EVENTS).hasAnyRole("CLIENT","ADMIN")
		.antMatchers(ALL_ENDPOINTS).hasRole("ADMIN")
		.anyRequest().authenticated();

	}

}

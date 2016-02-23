package com.samsonan.bplaces.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//TODO: encrypted passwords
		auth.userDetailsService(userService);		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	    http
	    .authorizeRequests()
	    .antMatchers("/", "/map").permitAll()
		.antMatchers("/restore").permitAll()
		.antMatchers("/places/list_admin").access("hasRole('ADMIN')")
		.antMatchers("/places/add*").access("hasAnyRole('USER','ADMIN')")
		.antMatchers("/places/edit-*").access("hasAnyRole('USER','ADMIN')")
		.antMatchers("/places/delete-*").access("hasAnyRole('USER','ADMIN')")
		.antMatchers("/places/**").permitAll()
		.antMatchers("/users/**").access("hasRole('ADMIN')")
		.antMatchers("/users/me").access("hasAnyRole('USER','ADMIN')")
		.and()
		    .formLogin().loginPage("/login")
		    .failureUrl("/login?error") //default
		    .usernameParameter("username").passwordParameter("password")		
		.and()
			.rememberMe().tokenValiditySeconds(1209600)
		.and()
		    .logout().logoutSuccessUrl("/login?logout") //default
		.and()
		    .csrf(); 		
	}
}

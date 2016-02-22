package com.samsonan.bplaces.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	/**
	 * TODO: use UserDetailsService to authenticate (p.259)
	 * http://www.mkyong.com/spring-security/spring-security-remember-me-example/
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
                   .withUser("samsonan").password("111").roles("USER");
		auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("USER","ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	    http
//	    .formLogin().and()
	    .authorizeRequests()
	    .antMatchers("/", "/map").permitAll()
		.antMatchers("/places/add*").access("hasRole('USER')")
		.antMatchers("/places/edit-*").access("hasRole('USER')")
		.antMatchers("/places/delete-*").access("hasRole('USER')")
		.antMatchers("/places/**").permitAll()
		.antMatchers("/admin/**").access("hasRole('ADMIN')")
		.and()
		    .formLogin().loginPage("/login")
		    .failureUrl("/login?error") //default
		    .usernameParameter("username").passwordParameter("password")		
		.and()
			.rememberMe().tokenValiditySeconds(1209600)
		.and()
		    .logout().logoutSuccessUrl("/login?logout") //default
		.and()
		    .csrf();//???? 		
	}
}

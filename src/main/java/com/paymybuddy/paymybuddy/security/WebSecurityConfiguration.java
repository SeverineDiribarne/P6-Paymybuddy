package com.paymybuddy.paymybuddy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	   @Autowired
	    private CustomerDetailsService customerDetailsService;
	
	/**
	 * configure HTTP security
	 */
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
					.antMatchers("/register").permitAll()
					.antMatchers("/", "/index").authenticated()
					.and()
			.formLogin()
					.loginPage("/login")
					.permitAll()
					.defaultSuccessUrl("/home",true)
					.and()
					.rememberMe()
					.and()
			.logout()
					.permitAll();
	}

	  @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(customerDetailsService).passwordEncoder(passwordEncoder());
	    }
	  
	  /**
		 * setPassWordEncoder Method
		 * @return instance of password encoder
		 */
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
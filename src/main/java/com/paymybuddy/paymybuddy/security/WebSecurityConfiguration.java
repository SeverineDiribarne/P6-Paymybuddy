package com.paymybuddy.paymybuddy.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    //@Autowired
    //private CustomerDetailsService customerDetailsService;
    
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 auth
		  .jdbcAuthentication()
		//  .passwordEncoder(new BCryptPasswordEncoder())
	      .dataSource(dataSource)
	      .usersByUsernameQuery("SELECT login, password, enabled"
	      		+ " FROM account"
	      		+ " WHERE login=?")
          .authoritiesByUsernameQuery("SELECT login, role"
          		+ " FROM authorities"
          		+ " JOIN account"
          		+ " ON account.id = authorities.account_id"
          		+ " WHERE login=?");
	}
	
	/**
	 * configure HTTP security
	 */
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
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
	
	/**
	 * setPassWordEncoder Method
	 * @return instance of password encoder
	 */
	@Bean
	public PasswordEncoder setPassWordEncoder() {
		return new BCryptPasswordEncoder();

	}
}


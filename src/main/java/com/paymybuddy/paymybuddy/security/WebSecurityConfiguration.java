package com.paymybuddy.paymybuddy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * configure HTTP security
	 */
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
					.antMatchers("/", "/home").authenticated()
					.and()
			.formLogin()
					.loginPage("/login")
					.permitAll()
<<<<<<< HEAD
					.defaultSuccessUrl("/home",true)
=======
					.defaultSuccessUrl("/transfer",true)
>>>>>>> feature/daoBranch
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
		return NoOpPasswordEncoder.getInstance();

	}
}
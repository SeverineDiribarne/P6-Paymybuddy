package com.paymybuddy.paymybuddy.security;

<<<<<<< HEAD

=======
>>>>>>> feature/register
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
<<<<<<< HEAD
import org.springframework.security.crypto.password.PasswordEncoder;
=======
>>>>>>> feature/register

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

<<<<<<< HEAD

    @Autowired
    private CustomerDetailsService customerDetailsService;
    
=======
	   @Autowired
	    private CustomerDetailsService customerDetailsService;
	
>>>>>>> feature/register
	/**
	 * configure HTTP security
	 */
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
<<<<<<< HEAD
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
	 * configure method with AuthenticationManagerBuilder
	 */
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(customerDetailsService).passwordEncoder(setPassWordEncoder());
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

=======
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
>>>>>>> feature/register

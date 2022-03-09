package com.paymybuddy.paymybuddy.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.paymybuddy.paymybuddy.model.Customer;

public class MyMainUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private transient Customer customer;
	
	/**
	 * Constructor
	 * @param customer
	 */
	public MyMainUser(Customer customer) {
		this.customer = customer;
	}

	/**
	 * get Authorities method
	 * @return empty list
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	/**
	 * get Password Method
	 * @return password
	 */
	@Override
	public String getPassword() {
		return customer.getAccount().getPassword();
	}

	/**
	 * get username
	 * @return email
	 */
	@Override
	public String getUsername() {
		return customer.getEmail();
	}

	/**
	 * isAccountNonExpired Method
	 * @return boolean result
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * isAccountNonLocked Method
	 * @return boolean result
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * isCredentialsNonExpired Method
	 * @return boolean result
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * isEnabled Method
	 * @return boolean result
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * getCustomerDetails Method
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;	
	}
}

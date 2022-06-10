package com.paymybuddy.paymybuddy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.CustomerDetailsDao;
import com.paymybuddy.paymybuddy.model.Customer;
@Service
public class CustomerDetailsService implements UserDetailsService {

	@Autowired
	private CustomerDetailsDao customerDetailsDao;
	
	/**
	 * load user by username
	 * @param username
	 * @return new MyMainUser 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerDetailsDao.getCustomer(username);
		if(customer == null) {
			throw new UsernameNotFoundException(username);
		}
		return new MyMainUser(customer);
	}
}
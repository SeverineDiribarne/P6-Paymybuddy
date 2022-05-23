package com.paymybuddy.paymybuddy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * 
	 */
	@Override
	public List<Customer> getAllCustomerRecipients(MyMainUser user) {
		return customerDao.getAllCustomerRecipients(user);
	}

	@Override
	public Customer getCustomerRecipientNameById(Customer customer) {
		return customerDao.getCustomerRecipientNameById(customer);
	}

	@Override
	public int getCustomerIdByName(Customer customer) {
		return customerDao.getCustomerIdByName(customer);
	}

	@Override
	public void registerNewCustomerIntoDatabase(Customer customer) {
		String encryptionPassword = passwordEncoder.encode(customer.getAccount().getPassword());
		customerDao.registerNewCustomerIntoDatabase(customer, encryptionPassword);
	}

	
//TODO voir si a garder methode en dessous
	@Override
	public int getCustomerIdByEmail(String email) {
		return customerDao.getCustomerIdByName(email);
	}
}
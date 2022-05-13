package com.paymybuddy.paymybuddy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.model.Customer;
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
	public List<Customer> getAllCustomerRecipients(int customerId) {
		return customerDao.getAllCustomerRecipients(customerId);
	}

	@Override
	public Customer getCustomerRecipientIdAndEmailById(int connection) {
		return customerDao.getCustomerRecipientIdAndEmailById(connection);
	}

	@Override
	public int getCustomerIdByEmail(String email) {
		return customerDao.getCustomerIdByEmail(email);
	}

	@Override
	public void registerNewCustomerIntoDatabase(String lastName, String firstName, String email, String password,
			String bankAccountName, String iban, String bic, String swift) {
		String encryptionPassword = passwordEncoder.encode(password);
		customerDao.registerNewCustomerIntoDatabase(lastName, firstName, email, encryptionPassword, bankAccountName, iban, bic, swift);
	}
}
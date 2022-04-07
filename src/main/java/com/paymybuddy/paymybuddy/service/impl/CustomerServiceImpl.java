package com.paymybuddy.paymybuddy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.service.contract.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

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
}

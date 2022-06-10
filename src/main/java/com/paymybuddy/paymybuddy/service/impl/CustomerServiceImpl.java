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
	 * get All Customer Recipients
	 * @param user
	 * @return list of all customerRecipient
	 */
	@Override
	public List<Customer> getAllCustomerRecipients(MyMainUser user) {
		return customerDao.getAllCustomerRecipients(user);
	}

	/**
	 * get CustomerRecipient Name By Id
	 * @param customer
	 * @return customer Recipient name
	 */
	@Override
	public Customer getCustomerRecipientNameById(Customer customer) {
		return customerDao.getCustomerRecipientNameById(customer);
	}

	/**
	 * get CustomerId By Name
	 * @param customer
	 * @return customerId
	 */
	@Override
	public int getCustomerIdByName(Customer customer) {
		return customerDao.getCustomerIdByName(customer);
	}

	/**
	 * register New Customer Into Database
	 * @param customer
	 */
	@Override
	public void registerNewCustomerIntoDatabase(Customer customer) {
		String encryptionPassword = passwordEncoder.encode(customer.getAccount().getPassword());
		customerDao.registerNewCustomerIntoDatabase(customer, encryptionPassword);
	}

	/**
	 * update Balance
	 * @param balance
	 * @param user
	 */
	@Override
	public void updateBalance(double balance, MyMainUser user) {
	customerDao.updateBalance(balance, user);
	}
}
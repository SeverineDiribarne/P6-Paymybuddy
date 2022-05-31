package com.paymybuddy.paymybuddy.service.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface CustomerService {
	
	public List<Customer> getAllCustomerRecipients( MyMainUser user);

	public Customer getCustomerRecipientNameById(Customer customer);
	
	public int getCustomerIdByName(Customer customer);

	public void registerNewCustomerIntoDatabase(Customer customer);

	public void updateBalance(double balance, MyMainUser user);
		
}

package com.paymybuddy.paymybuddy.dao.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.Customer;

public interface CustomerDao {
	
	public List<Customer> getAllFriends(int customerId);

	
}

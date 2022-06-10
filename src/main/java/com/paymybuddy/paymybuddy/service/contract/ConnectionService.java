package com.paymybuddy.paymybuddy.service.contract;

import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface ConnectionService {

	void addAConnection(MyMainUser user, Customer customer);

	int getConnectionIdByCustomersIdWithMainUser(MyMainUser user, Customer customer);

	void deleteAConnection(MyMainUser user, Customer customer);

	Customer getRecipientNameByRecipientId(Connection connection);
	
	Connection getConnectionByCustomers(Customer customerSource, Customer customerRecipient);

}
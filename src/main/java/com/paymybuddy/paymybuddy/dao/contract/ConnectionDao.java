package com.paymybuddy.paymybuddy.dao.contract;

import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface ConnectionDao {
	
	void addAConnection(MyMainUser user, Customer customer);

	int getConnectionIdByCustomersIdWithMainUser(MyMainUser user, Customer customer);

	void deleteAConnection(MyMainUser user, Customer customer);

	int getConnectionIdWithCustomersIdByConnection(double amount, Customer customerSource, Customer customerRecipient);

	Customer getRecipientNameByRecipientId(Connection connection);

	Connection getConnectionByCustomers(Customer customerSource, Customer customerRecipient);

}

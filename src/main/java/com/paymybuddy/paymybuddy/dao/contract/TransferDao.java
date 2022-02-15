package com.paymybuddy.paymybuddy.dao.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;

public interface TransferDao {

	List<Transfer> getTransfers(int customerId);

//	void addTransfer(String connection, String description, double amount);
//
//	List<Customer> addAConnection(String email);
//
//	int findFriendId(String email);
//
//	void deleteAConnection(String email);
//
//	List<Customer> getFriendsList();

}

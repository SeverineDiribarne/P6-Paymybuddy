package com.paymybuddy.paymybuddy.service.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;

public interface TransferService {

	List<Transfer> getTransfers(int customerId);

//	void addTransfer(String connection, String description, double amount);
//
//	void addAConnection(String email);
//
//	void deleteAConnection(String email);
//
//	List<Customer> getFriendsList();



	//	void getPaiement();
}

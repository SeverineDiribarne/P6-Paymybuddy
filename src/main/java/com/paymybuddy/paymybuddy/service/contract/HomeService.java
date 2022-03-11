package com.paymybuddy.paymybuddy.service.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;

public interface HomeService {

	Customer getBalance(int customerId);

	int addPaiement(int customerId, Object setDate, Object setFriend, Object setDescription, double amount);

	List<Transfer> getTransfers(int customerId);

}

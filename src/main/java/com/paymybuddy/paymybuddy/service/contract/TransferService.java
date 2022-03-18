package com.paymybuddy.paymybuddy.service.contract;

import java.util.Date;
import java.util.List;

import com.paymybuddy.paymybuddy.model.Connexion;
import com.paymybuddy.paymybuddy.model.Transfer;

public interface TransferService {

	List<Transfer> getTransfers(int customerId);

	void addPayment(Date date, Connexion connexion, String description, double amount);



}

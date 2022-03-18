package com.paymybuddy.paymybuddy.dao.contract;

import java.util.Date;
import java.util.List;

import com.paymybuddy.paymybuddy.model.Connexion;
import com.paymybuddy.paymybuddy.model.Transfer;

public interface TransferDao {

	List<Transfer> getTransfers(int mainUserId);

	void addPayment(Date date, Connexion connexion, String description, double amount);

}

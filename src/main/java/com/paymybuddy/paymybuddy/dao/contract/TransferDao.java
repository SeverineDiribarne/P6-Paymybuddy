package com.paymybuddy.paymybuddy.dao.contract;

import java.util.Date;
import java.util.List;

import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface TransferDao {

	List<Transfer> getListOfTransfers(MyMainUser user);

	void addPayment(Transfer transfer, Connection connection, double amount);

	Transfer getLastTransferId();

	
	
	//TODO voir si a garder toutes methodes en dessous
	void addPayment(Date date, Connection connection, String description, double amount);
	
	List<Transfer> getListOfTransfers(int customerSourceId );
}

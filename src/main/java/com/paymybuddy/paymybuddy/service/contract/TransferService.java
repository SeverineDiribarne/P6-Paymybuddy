package com.paymybuddy.paymybuddy.service.contract;

import java.util.Date;
import java.util.List;

//import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface TransferService {

	List<Transfer> getListOfTransfers(MyMainUser user);
	
	List<Transfer> getListOfTransfers(int customerSourceId);

//	void addPayment(Transfer transfer, Connection connection);

	void addPayment(Date date, int customerSourceId, int customerRecipientId, String description, double amount);
}

package com.paymybuddy.paymybuddy.dao.contract;

import java.util.Date;
import java.util.List;

import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Transfer;

public interface TransferDao {

	List<Transfer> getListOfTransfers(int mainUserId);

	void addPayment(Date date, Connection connection, String description, double amount);

	Transfer getLastTransferId();

}

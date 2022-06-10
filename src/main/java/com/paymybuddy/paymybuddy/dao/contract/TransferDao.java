package com.paymybuddy.paymybuddy.dao.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface TransferDao {

	List<Transfer> getListOfTransfers(MyMainUser user);

	void addPayment(Transfer transfer, Connection connection, double amount);

	Transfer getLastTransferId();

}
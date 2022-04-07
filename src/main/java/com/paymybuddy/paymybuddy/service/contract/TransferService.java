package com.paymybuddy.paymybuddy.service.contract;

import java.util.Date;
import java.util.List;

import com.paymybuddy.paymybuddy.model.Transfer;

public interface TransferService {

	List<Transfer> getListOfTransfers(int customerId);

	void addPayment(Date date, int customerSourceId, int customerRecipientId, String description, double amount);

	



}

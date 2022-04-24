package com.paymybuddy.paymybuddy.service.contract;

import java.util.Date;
import java.util.List;

import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.model.BankOperation;

public interface BankOperationService {
	
	List<BankOperation> getBankOperations(int customerId);

	void addPaymentFromBankToApp(Date date, String description, double amount, int source, int recipient);

	void addPaymentFromAppToBank(Date date, String description, double bankOperationAmount, int source, int recipient);
	
	BankOperation getLastOperationId();

	List<BankAccount> getName(int Id);

}

package com.paymybuddy.paymybuddy.dao.contract;

import java.util.Date;
import java.util.List;

import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.model.BankOperation;

public interface BankOperationDao {

	List<BankOperation> getBankOperations(int customerId);

	void addPaymentFromBankToApp(Date date, String description, double amount, int source, int recipient);

	BankOperation getLastOperationId();

	void addPaymentFromAppToBank(Date date, String description, double bankOperationAmount, int source, int recipient);

	List<BankAccount> getName(int id);

}

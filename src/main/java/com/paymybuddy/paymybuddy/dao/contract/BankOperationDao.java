package com.paymybuddy.paymybuddy.dao.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.model.BankOperation;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface BankOperationDao {

	List<BankOperation> getBankOperations(MyMainUser user);

	void addPaymentFromBankToApp(BankOperation bankOperation);

	BankOperation getLastOperationId();

	void addPaymentFromAppToBank(BankOperation bankOperation);

	List<BankAccount> getName(BankOperation bankOperation);

}
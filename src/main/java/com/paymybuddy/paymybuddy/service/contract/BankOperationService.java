package com.paymybuddy.paymybuddy.service.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.model.BankOperation;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface BankOperationService {
	
	List<BankOperation> getBankOperations(MyMainUser user);

	void addPaymentFromBankToApp(BankOperation bankOperation);

	void addPaymentFromAppToBank(MyMainUser user, BankOperation bankOperation);
	
	BankOperation getLastOperationId();

	List<BankAccount> getName(BankOperation bankOperation);

}

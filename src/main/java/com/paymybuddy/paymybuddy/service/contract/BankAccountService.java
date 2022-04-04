package com.paymybuddy.paymybuddy.service.contract;

import java.util.Date;
import java.util.List;

import com.paymybuddy.paymybuddy.model.BankOperation;

public interface BankAccountService {

	public int getBankAccountId(String iban);
	
//	void addWidrawalFromBank(int owner, Date date, int bankId, String description, double amount);
//	
//	void addDepositeToBank(int bankId, Date date, int owner, String description, double amount);
//	
//	List<BankOperation> getBankOperations();
}

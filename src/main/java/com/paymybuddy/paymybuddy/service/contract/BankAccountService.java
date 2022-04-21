package com.paymybuddy.paymybuddy.service.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.BankAccount;

public interface BankAccountService {

	public int getBankAccountId(int customerId);

	List<BankAccount> getAllElementsOfBankAccount(int customerId);
	
	
}

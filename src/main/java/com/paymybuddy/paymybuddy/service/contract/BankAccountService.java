package com.paymybuddy.paymybuddy.service.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface BankAccountService {

	public int getBankAccountId(MyMainUser user);

	List<BankAccount> getAllElementsOfBankAccount(MyMainUser user);
	
	
}

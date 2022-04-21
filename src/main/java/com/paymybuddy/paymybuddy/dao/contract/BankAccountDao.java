package com.paymybuddy.paymybuddy.dao.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.BankAccount;

public interface BankAccountDao {

	int getBankAccountId(int customerId);

	List<BankAccount> getAllElementsOfBankAccount(int customerId);

}

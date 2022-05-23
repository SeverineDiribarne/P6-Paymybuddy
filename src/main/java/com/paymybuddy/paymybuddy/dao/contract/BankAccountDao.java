package com.paymybuddy.paymybuddy.dao.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface BankAccountDao {

	int getBankAccountId(MyMainUser user);

	List<BankAccount> getAllElementsOfBankAccount(MyMainUser user);

}

package com.paymybuddy.paymybuddy.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.BankAccountDao;
import com.paymybuddy.paymybuddy.dao.impl.BankAccountDaoImpl;
import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	BankAccountDao bankAccountDao = new BankAccountDaoImpl();
	
	/**
	 * get bankaccountId
	 * @param user
	 * @return bankaccountId
	 */
	@Override
	public int getBankAccountId(MyMainUser user) {
		return bankAccountDao.getBankAccountId(user);
	}
	
	/**
	 * get all elements of bankAccount
	 * @param user
	 * @return list of all elements of bankAccount
	 */
	@Override
	public List<BankAccount> getAllElementsOfBankAccount(MyMainUser user) {
		return bankAccountDao.getAllElementsOfBankAccount(user);
	}
}
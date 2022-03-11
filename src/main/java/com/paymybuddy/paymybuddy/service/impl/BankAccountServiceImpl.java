package com.paymybuddy.paymybuddy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.paymybuddy.paymybuddy.dao.contract.BankAccountDao;
import com.paymybuddy.paymybuddy.service.contract.BankAccountService;

public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	BankAccountDao bankAccountDao;
	
	@Override
	public int getBankAccountId(String iban) {
		return bankAccountDao.getBankAccountId(iban);
	}

}

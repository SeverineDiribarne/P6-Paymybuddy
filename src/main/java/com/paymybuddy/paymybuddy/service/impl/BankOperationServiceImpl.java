package com.paymybuddy.paymybuddy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.BankOperationDao;
import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.dao.contract.HomeDao;
import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.model.BankOperation;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.BankOperationService;

@Service
public class BankOperationServiceImpl implements BankOperationService {

	@Autowired
	BankOperationDao bankOperationDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	HomeDao homeDao;
	/**
	 * 
	 */
	@Override
	public List<BankOperation> getBankOperations(MyMainUser user) {
		return bankOperationDao.getBankOperations(user);
	}

	/**
	 * Add Payment From app To Bank
	 */
	@Override
	public void addPaymentFromBankToApp(BankOperation bankOperation) {
		bankOperationDao.addPaymentFromBankToApp(bankOperation);	
		BankOperation lastBankOperation = bankOperationDao.getLastOperationId();
		customerDao.updateCustomerBalanceAfterPaymentFromBankToApp(lastBankOperation.getBankOperationId());
	}

	/**
	 * Add payment From App To bank
	 */
	@Override
	public void addPaymentFromAppToBank(MyMainUser user, BankOperation bankOperation) {
		bankOperationDao.addPaymentFromAppToBank(bankOperation);	
		BankOperation lastBankOperation = bankOperationDao.getLastOperationId();
		customerDao.updateCustomerBalanceAfterPaymentFromAppToBank(lastBankOperation.getBankOperationId());
		homeDao.monetizationAppFromAppToBank(bankOperation.getSource(), bankOperation.getBankOperationId());
	}
	
	/**
	 * Get last Operation Id 
	 */
	@Override
	public BankOperation getLastOperationId() {
		return bankOperationDao.getLastOperationId();
	}

	@Override
	public List<BankAccount> getName(BankOperation bankOperation) {
		return bankOperationDao.getName(bankOperation);
	}
}
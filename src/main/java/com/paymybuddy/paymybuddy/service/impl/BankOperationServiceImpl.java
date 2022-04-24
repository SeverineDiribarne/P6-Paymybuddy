package com.paymybuddy.paymybuddy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.BankOperationDao;
import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.dao.contract.HomeDao;
import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.model.BankOperation;
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
	public List<BankOperation> getBankOperations(int customerId) {
		return bankOperationDao.getBankOperations(customerId);
	}

	/**
	 * Add Payment From app To Bank
	 */
	@Override
	public void addPaymentFromBankToApp(Date date, String description, double amount,int source, int recipient) {
		bankOperationDao.addPaymentFromBankToApp(date, description, amount, source, recipient);	
		BankOperation bankOperation = bankOperationDao.getLastOperationId();
		customerDao.updateCustomerBalanceAfterPaymentFromBankToApp(bankOperation.getBankOperationId());
		//homeDao.monetizationAppFromBankToApp(source, bankOperation.getBankOperationId());
	}

	/**
	 * Add payment From App To bank
	 */
	@Override
	public void addPaymentFromAppToBank(Date date, String description, double bankOperationAmount, int source,
			int recipient) {
		bankOperationDao.addPaymentFromAppToBank(date, description, bankOperationAmount, source, recipient);	
		BankOperation bankOperation = bankOperationDao.getLastOperationId();
		customerDao.updateCustomerBalanceAfterPaymentFromAppToBank(bankOperation.getBankOperationId());
		homeDao.monetizationAppFromAppToBank(source, bankOperation.getBankOperationId());
	}
	
	/**
	 * Get last Operation Id 
	 */
	@Override
	public BankOperation getLastOperationId() {
		return bankOperationDao.getLastOperationId();
	}

	@Override
	public List<BankAccount> getName(int id) {
		return bankOperationDao.getName(id);
	}
}
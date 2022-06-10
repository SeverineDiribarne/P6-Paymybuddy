package com.paymybuddy.paymybuddy.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.BankOperationDao;
import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.dao.contract.HomeDao;
import com.paymybuddy.paymybuddy.dto.BankTransferDisplay;
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
	 * Get bank Operations
	 * @param user
	 * @return list of bankOperations
	 */
	@Override
	public List<BankOperation> getBankOperations(MyMainUser user) {
		return bankOperationDao.getBankOperations(user);
	}

	/**
	 * Add Payment From app To Bank
	 * @param bankOperation
	 */
	@Override
	public void addPaymentFromBankToApp(BankOperation bankOperation) {
		bankOperationDao.addPaymentFromBankToApp(bankOperation);	
		BankOperation lastBankOperation = bankOperationDao.getLastOperationId();
		customerDao.updateCustomerBalanceAfterPaymentFromBankToApp(lastBankOperation.getBankOperationId());
	}

	/**
	 * Add payment From App To bank
	 * @param user
	 * @param bankOperation
	 */
	@Override
	public void addPaymentFromAppToBank(MyMainUser user, BankOperation bankOperation) {
		bankOperation.setBankOperationAmount(bankOperation.getBankOperationAmount()*(-1));
		bankOperationDao.addPaymentFromAppToBank(bankOperation);	
		BankOperation lastBankOperation = bankOperationDao.getLastOperationId();
		customerDao.updateCustomerBalanceAfterPaymentFromAppToBank(lastBankOperation.getBankOperationId());
		homeDao.monetizationAppFromAppToBank(bankOperation.getSource(), bankOperation.getBankOperationId());
	}
	
	/**
	 * Get last Operation Id 
	 * @return last OperationId
	 */
	@Override
	public BankOperation getLastOperationId() {
		return bankOperationDao.getLastOperationId();
	}

	/**
	 * Get BankAccount Name for display
	 * @param bankOperation
	 * @return list of bankAccount names
	 */
	@Override
	public List<BankAccount> getName(BankOperation bankOperation) {
		return bankOperationDao.getName(bankOperation);
	}

	/**
	 * get bank Operation paginated
	 * @param pageable
	 * @param user
	 * @return  pagination of bank operations 
	 */
	@Override
	public Page<BankTransferDisplay> getBankOperationsPaginated(Pageable pageable, MyMainUser user) {
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<BankOperation> bankOperations = bankOperationDao.getBankOperations(user);
        
		List<BankTransferDisplay> allBankTransferDisplayList = new ArrayList<>();
		for(BankOperation bankOperation : bankOperations) {
			if(bankOperation.getDescription().equals("Payment from Bank to App")) {
				List<BankAccount> sourceName =  bankOperationDao.getName(bankOperation);
				String recipientName = user.getCustomer().getFirstName() + " " + user.getCustomer().getLastName();
				BankTransferDisplay bankTransferDisplay = new BankTransferDisplay(bankOperation.getDate(),sourceName.get(0).getBankAccountName(), recipientName, bankOperation.getDescription(), bankOperation.getBankOperationAmount());
				allBankTransferDisplayList.add(bankTransferDisplay);
			}
			else {
				String sourceName =  user.getCustomer().getFirstName() + " " + user.getCustomer().getLastName();
				List<BankAccount> recipientName = bankOperationDao.getName(bankOperation);
				BankTransferDisplay bankTransferDisplay = new BankTransferDisplay(bankOperation.getDate(),sourceName, recipientName.get(0).getBankAccountName(), bankOperation.getDescription(), bankOperation.getBankOperationAmount());
				allBankTransferDisplayList.add(bankTransferDisplay);
			}
		}
		 List<BankTransferDisplay> filteredBankTransfers = null;
	        
	        if (allBankTransferDisplayList.size() < startItem) {
	        	filteredBankTransfers = Collections.emptyList();
	        } else {
	            int toIndex = Math.min(startItem + pageSize, allBankTransferDisplayList.size());
	            filteredBankTransfers = allBankTransferDisplayList.subList(startItem, toIndex);
	        }
	        return new PageImpl<>(filteredBankTransfers, PageRequest.of(currentPage, pageSize), allBankTransferDisplayList.size());
		}
}
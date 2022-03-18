package com.paymybuddy.paymybuddy.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.TransferDao;
import com.paymybuddy.paymybuddy.model.Connexion;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.service.contract.TransferService;

@Service
public class TransferServiceImpl implements TransferService{

	@Autowired
	TransferDao transferDao;

	//private static final Logger logger = LogManager.getLogger(); 

	/**
	 * 
	 */
	@Override
	public List<Transfer> getTransfers(int mainUserId) {
		return transferDao.getTransfers(mainUserId);
	}

	/**
	 * 
	 */
	@Override
	public void addPayment(Date date, Connexion connexion, String description, double amount) {
		if(amount < 0) {
		transferDao.addPayment (date, connexion, description, amount);
		}
		else {
			double	negativeAmount = amount * (-1);
			Connexion reverseConnection = new Connexion();
			connexion.setCustomerSource(connexion.getCustomerDestinataire());
			connexion.setCustomerDestinataire(connexion.getCustomerSource());
			transferDao.addPayment (date, reverseConnection, description, negativeAmount);
		}
		

	}

	/**
	 * 
	 */
//	@Override
//	public void addAConnection(int customerId, String email) {
//
//
//		transferDao.addAConnection(customerId, email);

//		else {
//			logger.debug("Veuillez écrire une adresse email valide. "); 
//		}
//	}
	//
	//	@Override
	//	public void deleteAConnection(String email) {
	//		if(email != null) {
	//			int friendId = transferDao.findFriendId(email);
	//			transferDao.deleteAConnection(email);
	//		}
	//		else {
	//			logger.debug("Veuillez écrire une adresse email valide. "); 
	//		}
	//		
	//	}
	//
	//	@Override
	//	public List<Customer> getFriendsList() {
	//		return transferDao.getFriendsList();
	//	}

	//	@Autowired
	//	BankAccountDao bankAccountDao;
	//	
	//	@Override
	//	public void getPaiement() {
	//	bankAccountDao.getBankAccount();
	//		
	//	}

}

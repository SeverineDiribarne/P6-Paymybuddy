package com.paymybuddy.paymybuddy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.ConnectionDao;
import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.dao.contract.TransferDao;
import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.TransferService;

@Service
public class TransferServiceImpl implements TransferService{

	@Autowired
	TransferDao transferDao;
	
	@Autowired
	ConnectionDao connectionDao;
	
	@Autowired
	CustomerDao customerDao;
	

	/**
	 * get List Of transfers
	 * @param mainUserId
	 */
	@Override
	public List<Transfer> getListOfTransfers(MyMainUser user) {
		return transferDao.getListOfTransfers(user);
	}
	
 //TODO factoriser les 2 methodes en dessous
	/**
	 * Save Customer Source Attributes
	 * @param customerSourceId
	 * @param connection
	 */
	private void saveCustomerSourceAttributes(int customerSourceId, Connection connection) {
		List<Customer> customerSourceAttributes = customerDao.getAllInformationsOfCustomerById(customerSourceId);
		for (Customer customer : customerSourceAttributes) {
			connection.getCustomerSource().setFirstName(customer.getFirstName());
			connection.getCustomerSource().setLastName(customer.getLastName());
			connection.getCustomerSource().setEmail(customer.getEmail());
			connection.getCustomerSource().setBalance(customer.getBalance());
			connection.getCustomerSource().setCustomerId(customerSourceId);
		}
	}
	/**
	 * @param customerRecipientId
	 * @param connection
	 */
	private void saveCustomerRecipientAttributes(int customerRecipientId, Connection connection) {
		List<Customer> customerRecipientAttributes = customerDao.getAllInformationsOfCustomerById(customerRecipientId);
		for (Customer customer : customerRecipientAttributes) {
		connection.getCustomerRecipient().setFirstName(customer.getFirstName());
		connection.getCustomerRecipient().setLastName(customer.getLastName());
		connection.getCustomerRecipient().setEmail(customer.getEmail());
		connection.getCustomerRecipient().setBalance(customer.getBalance());
		connection.getCustomerRecipient().setCustomerId(customerRecipientId);
		}
	}
//	/**
//	 * Add a payment
//	 * @param date
//	 * @param customerSourceId
//	 * @param customerRecipientId
//	 * @param description
//	 * @param amount
//	 */
//	@Override
//	public void addPayment(Transfer transfer, Connection connection) {
//		if(transfer.getAmount() > 0) {
//			//negative amount for main user
//			double negativeAmount = transfer.getAmount() * (-1);
//			int connectionId = connectionDao.getConnectionIdWithCustomersIdByConnection(negativeAmount,connection.getCustomerSource(), connection.getCustomerRecipient());
//			Connection newConnection = new Connection(connectionId, connection.getCustomerSource().getCustomerId(), connection.getCustomerRecipient().getCustomerId());
//			saveCustomerSourceAttributes(connection.getCustomerSource().getCustomerId(), newConnection);
//			saveCustomerRecipientAttributes(connection.getCustomerRecipient().getCustomerId(), newConnection);
//			//add negative payment
//			transferDao.addPayment (transfer, newConnection, negativeAmount);
//			// balance calculation for customerSource
//			Transfer numTransfer = transferDao.getLastTransferId();
//			customerDao.updateCustomerBalance(connection.getCustomerSource().getCustomerId(), numTransfer.getTransferId());
//			customerDao.monetizationApp(connection.getCustomerSource().getCustomerId(), numTransfer.getTransferId());
//			
//			//positive amount for recipient customer
//			int secondConnectionId = connectionDao.getConnectionIdWithCustomersIdByConnection(transfer.getAmount(),
//					connection.getCustomerRecipient(), connection.getCustomerSource());
//			Connection reverseConnection = new Connection(secondConnectionId, connection.getCustomerRecipient().getCustomerId(),
//					connection.getCustomerSource().getCustomerId());
//			saveCustomerRecipientAttributes(connection.getCustomerRecipient().getCustomerId(), reverseConnection);
//			saveCustomerSourceAttributes(connection.getCustomerSource().getCustomerId(), reverseConnection);
//			transferDao.addPayment (transfer, reverseConnection, transfer.getAmount());
//			// balance calculation for customerRecipient
//			transfer = transferDao.getLastTransferId();
//			customerDao.updateCustomerBalance(connection.getCustomerRecipient().getCustomerId(), transfer.getTransferId());	
//		}
//	}
	//TODO Voir si a garder methodes en dessous
	@Override
	public List<Transfer> getListOfTransfers(int customerSourceId) {
		return transferDao.getListOfTransfers(customerSourceId);
	}
	
	@Override
	public void addPayment(Date date, int customerSourceId, int customerRecipientId, String description,
			double amount) {
		if(amount > 0) {
			//negative amount for main user
			double negativeAmount = amount * (-1);
			int connectionId = connectionDao.getConnectionIdWithCustomersIdByConnection(negativeAmount,customerSourceId, customerRecipientId);
			Connection newConnection = new Connection(connectionId, customerSourceId, customerRecipientId);
			saveCustomerSourceAttributes(customerSourceId, newConnection);
			saveCustomerRecipientAttributes(customerRecipientId, newConnection);
			//add negative payment
			transferDao.addPayment (date, newConnection, description, negativeAmount);
			// balance calculation for customerSource
			Transfer numTransfer = transferDao.getLastTransferId();
			customerDao.updateCustomerBalance(customerSourceId, numTransfer.getTransferId());
			customerDao.monetizationApp(customerSourceId, numTransfer.getTransferId());
			
			//positive amount for recipient customer
			int secondConnectionId = connectionDao.getConnectionIdWithCustomersIdByConnection(amount,
					customerRecipientId, customerSourceId);
			Connection reverseConnection = new Connection(secondConnectionId, customerRecipientId,
					customerSourceId);
			saveCustomerRecipientAttributes(customerRecipientId, reverseConnection);
			saveCustomerSourceAttributes(customerSourceId, reverseConnection);
			transferDao.addPayment (date, reverseConnection,description, amount);
			// balance calculation for customerRecipient
			Transfer transfer = transferDao.getLastTransferId();
			customerDao.updateCustomerBalance(customerRecipientId, transfer.getTransferId());	
		}
	}
	
}
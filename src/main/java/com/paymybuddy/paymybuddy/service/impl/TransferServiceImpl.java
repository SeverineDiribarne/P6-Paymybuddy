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
	public List<Transfer> getListOfTransfers(int mainUserId) {
		return transferDao.getListOfTransfers(mainUserId);
	}

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
	/**
	 * Add a payment
	 * @param date
	 * @param customerSourceId
	 * @param customerRecipientId
	 * @param description
	 * @param amount
	 */
	@Override
	public void addPayment(Date date, int customerSourceId, int customerRecipientId, String description, double amount) {
		if(amount > 0) {
			//negative amount for main user
			double negativeAmount = amount * (-1);
			int connectionId = connectionDao.getConnectionIdByCustomersId(customerSourceId,customerRecipientId);
			Connection connection = new Connection(connectionId, customerSourceId, customerRecipientId);
			saveCustomerSourceAttributes(customerSourceId, connection);
			saveCustomerRecipientAttributes(customerRecipientId, connection);
			//add negative payment
			transferDao.addPayment (date, connection, description, negativeAmount);
			// balance calculation for customerSource
			Transfer transfer = transferDao.getLastTransferId();
			customerDao.updateCustomerBalance(customerSourceId, transfer.getTransferId());
			customerDao.monetizationApp(customerSourceId, transfer.getTransferId());
			
			//positive amount for recipient customer
			int secondConnectionId = connectionDao.getConnectionIdByCustomersId(customerRecipientId, customerSourceId);
			Connection reverseConnection = new Connection(secondConnectionId, customerRecipientId, customerSourceId);
			saveCustomerRecipientAttributes(customerRecipientId, reverseConnection);
			saveCustomerSourceAttributes(customerSourceId, reverseConnection);
			transferDao.addPayment (date, reverseConnection, description, amount);
			// balance calculation for customerSource
			transfer = transferDao.getLastTransferId();
			customerDao.updateCustomerBalance(customerRecipientId, transfer.getTransferId());	
		}
	}
}
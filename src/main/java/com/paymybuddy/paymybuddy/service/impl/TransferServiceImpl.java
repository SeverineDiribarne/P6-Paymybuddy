package com.paymybuddy.paymybuddy.service.impl;

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

	@Override
	public void addPayment(Transfer transfer, MyMainUser user) {
		if(transfer.getAmount() > 0) {
			//negative amount for main user
			double negativeAmount = transfer.getAmount() * (-1);
			int connectionId = connectionDao.getConnectionIdWithCustomersIdByConnection(negativeAmount,user.getCustomer().getCustomerId(), transfer.getCustomerRecipient().getCustomerId());
			Connection newConnection = new Connection(connectionId, user.getCustomer().getCustomerId(), transfer.getCustomerRecipient().getCustomerId());
			saveCustomerSourceOrRecipientAttributes(user.getCustomer().getCustomerId(),transfer.getCustomerRecipient().getCustomerId(), newConnection);
			//add negative payment
			transferDao.addPayment (transfer, newConnection, negativeAmount);
			// balance calculation for customerSource
			Transfer numTransfer = transferDao.getLastTransferId();
			customerDao.updateCustomerBalance(user.getCustomer().getCustomerId(), numTransfer.getTransferId());
			customerDao.monetizationApp(user.getCustomer().getCustomerId(), numTransfer.getTransferId());

			//positive amount for recipient customer
			int secondConnectionId = connectionDao.getConnectionIdWithCustomersIdByConnection(transfer.getAmount(),
					transfer.getCustomerRecipient().getCustomerId(), user.getCustomer().getCustomerId());
			Connection reverseConnection = new Connection(secondConnectionId, transfer.getCustomerRecipient().getCustomerId(),
					user.getCustomer().getCustomerId());
			saveCustomerSourceOrRecipientAttributes(transfer.getCustomerRecipient().getCustomerId(), user.getCustomer().getCustomerId(), reverseConnection);
			transferDao.addPayment (transfer, reverseConnection,transfer.getAmount());
			// balance calculation for customerRecipient
			Transfer lastTransfer = transferDao.getLastTransferId();
			customerDao.updateCustomerBalance(transfer.getCustomerRecipient().getCustomerId(), lastTransfer.getTransferId());
		}
	}

	/**
	 * Save Customer Source or Customer Recipient Attributes
	 * @param customerSourceId
	 * @param customerRecipientId
	 * @param connection
	 */
	private void saveCustomerSourceOrRecipientAttributes(int customerSourceId, int customerRecipientId,  Connection connection) {
		if (customerSourceId !=0) {
			List<Customer> customerSourceAttributes = customerDao.getAllInformationsOfCustomerById(customerSourceId);
			for (Customer customer : customerSourceAttributes) {
				connection.getCustomerSource().setFirstName(customer.getFirstName());
				connection.getCustomerSource().setLastName(customer.getLastName());
				connection.getCustomerSource().setEmail(customer.getEmail());
				connection.getCustomerSource().setBalance(customer.getBalance());
				connection.getCustomerSource().setCustomerId(customerSourceId);
			}
		}
		if (customerRecipientId !=0) {
			List<Customer> customerRecipientAttributes = customerDao.getAllInformationsOfCustomerById(customerRecipientId);
			for (Customer customer : customerRecipientAttributes) {
				connection.getCustomerRecipient().setFirstName(customer.getFirstName());
				connection.getCustomerRecipient().setLastName(customer.getLastName());
				connection.getCustomerRecipient().setEmail(customer.getEmail());
				connection.getCustomerRecipient().setBalance(customer.getBalance());
				connection.getCustomerRecipient().setCustomerId(customerRecipientId);
			}
		}	
	}
}
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
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.paymybuddy.dao.contract.ConnectionDao;
import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.dao.contract.TransferDao;
import com.paymybuddy.paymybuddy.dto.TransferDisplay;
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
	 * @return list of all transfers
	 */
	@Override
	public List<Transfer> getListOfTransfers(MyMainUser user) {
		return transferDao.getListOfTransfers(user);
	}

	/**
	 * add payment
	 * @param transfer
	 * @param user
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addPayment(Transfer transfer, MyMainUser user) throws Exception {
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
	
	/**
	 * get Transfers Paginated
	 * @param pageable
	 * @param user
	 * @return pagination of all transfers 
	 */
	@Override
	public Page<TransferDisplay> getTransfersPaginated(Pageable pageable, MyMainUser user) {
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Transfer> allTransfers = getListOfTransfers(user);
        
		String userMainName = "";
		List<TransferDisplay> allTransfersDisplayList = new ArrayList<>();
		for(Transfer transfer : allTransfers) {
			Connection connection = transfer.getConnection();
			userMainName = user.getCustomer().getFirstName() + " " + user.getCustomer().getLastName();
			Customer customerRecipient = customerDao.getCustomerRecipientNameById(connection.getCustomerRecipient());
			String customerRecipientName = customerRecipient.getFirstName() + " " + customerRecipient.getLastName();	
			if (transfer.getAmount()>=0) {
				String temp = "";
				temp = userMainName;
				userMainName = customerRecipientName;
				customerRecipientName = temp;	
			}
			TransferDisplay transferDisplay = new TransferDisplay(transfer.getDate(),userMainName, customerRecipientName, transfer.getDescription(), transfer.getAmount());
			allTransfersDisplayList.add(transferDisplay);			
		}

        List<TransferDisplay> filteredTransfers = null;
        
        if (allTransfersDisplayList.size() < startItem) {
        	filteredTransfers = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allTransfersDisplayList.size());
            filteredTransfers = allTransfersDisplayList.subList(startItem, toIndex);
        }
        return new PageImpl<>(filteredTransfers, PageRequest.of(currentPage, pageSize), allTransfersDisplayList.size());
	}
}
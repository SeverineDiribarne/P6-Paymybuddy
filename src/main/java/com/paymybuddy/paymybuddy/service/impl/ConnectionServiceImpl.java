package com.paymybuddy.paymybuddy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.paymybuddy.dao.contract.ConnectionDao;
import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.dao.contract.TransferDao;
import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.ConnectionService;

@Service
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	ConnectionDao connectionDao;
	
	@Autowired
	TransferDao transferDao;
	
	@Autowired
	CustomerDao customerDao;

	/**
	 * add A Connection
	 * @param user
	 * @param customer
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addAConnection(MyMainUser user, Customer customer) {
		connectionDao.addAConnection( user, customer);

	}

	/**
	 * get ConnectionId By CustomersId With MainUser
	 * @param user
	 * @param customer
	 * @return connectionId
	 */
	@Override
	public int getConnectionIdByCustomersIdWithMainUser(MyMainUser user, Customer customer) {
		return connectionDao.getConnectionIdByCustomersIdWithMainUser(user, customer);
	}

	/**
	 * delete A Connection
	 * @param user
	 * @param customer
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteAConnection(MyMainUser user, Customer customer)throws Exception {
		Customer customerRecipient = customerDao.getCustomerByEmail(customer.getEmail());
		Connection connectionIdFromUserToCustomer = connectionDao.getConnectionByCustomers(user.getCustomer(), customerRecipient);
		Connection connectionIdFromCustomerToUser = connectionDao.getConnectionByCustomers(customerRecipient, user.getCustomer());
		transferDao.deleteTransfersOfCustomer(connectionIdFromUserToCustomer);
		transferDao.deleteTransfersOfCustomer(connectionIdFromCustomerToUser);
		connectionDao.deleteAConnection( user, customer);

	}
	/**
	 * get RecipientName By RecipientId
	 * @param connection
	 * @return name of customerRecipient
	 */
	@Override
	public Customer getRecipientNameByRecipientId(Connection connection) {
		return connectionDao.getRecipientNameByRecipientId( connection);
	}

	/**
	 * get Connection By Customers
	 * @param customerSource
	 * @param customerRecipient
	 * @return connection
	 */
	@Override
	public Connection getConnectionByCustomers(Customer customerSource, Customer customerRecipient) {
		return connectionDao.getConnectionByCustomers( customerSource, customerRecipient);
	}
}
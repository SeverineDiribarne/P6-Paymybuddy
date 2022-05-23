package com.paymybuddy.paymybuddy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.ConnectionDao;
import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.ConnectionService;

@Service
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	ConnectionDao connectionDao;
	
	@Override
	public void addAConnection(MyMainUser user, Customer customer) {
		connectionDao.addAConnection( user, customer);
		
	}

	@Override
	public int getConnectionIdByCustomersIdWithMainUser(MyMainUser user, Customer customer) {
		return connectionDao.getConnectionIdByCustomersIdWithMainUser(user, customer);
	}


	@Override
	public void deleteAConnection(MyMainUser user, Customer customer) {
		connectionDao.deleteAConnection( user, customer);
		
	}

	@Override
	public Customer getRecipientNameByRecipientId(Connection connection) {
		return connectionDao.getRecipientNameByRecipientId( connection);
	}

	@Override
	public Connection getConnectionByCustomers(Customer customerSource, Customer customerRecipient) {
		return connectionDao.getConnectionByCustomers( customerSource, customerRecipient);
	}

	//TODO Voir si a garder
	@Override
	public int getConnectionIdByCustomersId(int customerSourceId, int customerRecipientId) {
		return connectionDao.getConnectionIdByCustomersId(customerSourceId, customerRecipientId);
	}
}

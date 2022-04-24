package com.paymybuddy.paymybuddy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.ConnectionDao;
import com.paymybuddy.paymybuddy.service.contract.ConnectionService;

@Service
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	ConnectionDao connectionDao;
	
	@Override
	public void addAConnection(int customerSourceId, String email) {
		connectionDao.addAConnection( customerSourceId, email);
		
	}

	@Override
	public int getConnectionIdByCustomersId(int customerSourceId, int customerRecipientId) {
		return connectionDao.getConnectionIdByCustomersId(customerSourceId, customerRecipientId);
	}


//	@Override
//	public void deleteAConnection(int customerId, String email) {
//		connectionDao.deleteAConnection( customerId, email);
//		
//	}

}

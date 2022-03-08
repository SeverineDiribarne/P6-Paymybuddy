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
	public void addAConnection(int customerId, String email) {
		connectionDao.addAConnection( customerId, email);
		
	}

//	@Override
//	public void deleteAConnection(int customerId, String email) {
//		connectionDao.deleteAConnection( customerId, email);
//		
//	}

}

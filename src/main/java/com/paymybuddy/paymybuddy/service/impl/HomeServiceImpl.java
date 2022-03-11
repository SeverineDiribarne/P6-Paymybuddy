package com.paymybuddy.paymybuddy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paymybuddy.paymybuddy.dao.contract.HomeDao;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.service.contract.HomeService;


@Service
public class HomeServiceImpl implements HomeService {

@Autowired
public HomeDao homeDao;
	
	@Override
	public Customer getBalance(int customerId) {
		return homeDao.getBalance(customerId);	
	}

	@Override
	public int addPaiement(int customerId, Object setDate, Object setFriend, Object setDescription, double amount) {
		return homeDao.addPaiement(customerId, setDate, setFriend, setDescription, amount);
		
	}

	@Override
	public List<Transfer> getTransfers(int customerId) {	
		return homeDao.getTransfers(customerId);	
	}
}

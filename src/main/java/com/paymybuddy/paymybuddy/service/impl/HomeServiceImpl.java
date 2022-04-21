package com.paymybuddy.paymybuddy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paymybuddy.paymybuddy.dao.contract.HomeDao;
import com.paymybuddy.paymybuddy.model.BankTransferDisplay;
import com.paymybuddy.paymybuddy.model.Customer;
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
	public List<BankTransferDisplay> getBankOperations(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.paymybuddy.paymybuddy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paymybuddy.paymybuddy.dao.contract.HomeDao;
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
}

package com.paymybuddy.paymybuddy.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paymybuddy.paymybuddy.dao.contract.HomeDao;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.HomeService;


@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	public HomeDao homeDao;

	/**
	 * get balance
	 * @param user
	 * @return balance of customer
	 */
	@Override
	public Customer getBalance(MyMainUser user) {
		return homeDao.getBalance(user);	
	}
}
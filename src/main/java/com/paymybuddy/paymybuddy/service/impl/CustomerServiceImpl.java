package com.paymybuddy.paymybuddy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.service.contract.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao userDao;

	/**
	 * getFriendsList Method 
	 * @return list of friends
	 */
	@Override
	public List<String> getFriendsList(String email) {
		return userDao.getFriendsList(email);
	}
	
	/**
	 * addFriend Method
	 * @return 
	 */
	@Override
	public boolean addFriend(String mail) {
		if (userDao.customerExist(mail)) {
			userDao.addFriend(mail);
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @return
	 */
	@Override
	public boolean deleteFriend(String mail) {
		// TODO Auto-generated method stub
		return false;
	}
}

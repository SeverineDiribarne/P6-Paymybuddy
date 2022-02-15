package com.paymybuddy.paymybuddy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	/**
	 * customerExist Method
	 * @return if Customer not exist = reponse false or Customer exist = reponse true
	 */
	@Override
	public boolean customerExist(String mail) {
		boolean userExist = false;


		return userExist;
	}
	
	/**
	 * addFriend Method
	 * @return friend added
	 */
	@Override
	public boolean addFriend(String mail) {
		//TODO A finir
		boolean friendAdded = false;
		
		return friendAdded;
	}
/**
 * getFriendsList Method
 * @return list of friends
 */
	@Override
	public List<String> getFriendsList(String mail) {
		List<String> friendsList = new ArrayList<>();
		
		//TODO A finir
			
				
		return friendsList;
	}
	/**
	 * getMoney Method
	 */
	@Override
	public void getMoney(double amount) { // manque le lastName et firstName de l'utilisateur connecté
		// TODO Auto-generated method stub
		
	}
	
	
}

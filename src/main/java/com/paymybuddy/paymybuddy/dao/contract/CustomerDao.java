package com.paymybuddy.paymybuddy.dao.contract;

import java.util.List;

public interface CustomerDao {

	public boolean customerExist(String mail);

	public boolean addFriend(String mail);
	
	public List<String> getFriendsList(String mail);

	public void getMoney(double amount);
	
	
	
	
}

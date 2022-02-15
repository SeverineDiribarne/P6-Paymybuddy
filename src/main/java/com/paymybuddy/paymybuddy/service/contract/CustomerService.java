package com.paymybuddy.paymybuddy.service.contract;

import java.util.List;

public interface CustomerService {

	public boolean addFriend(String mail);

	public boolean deleteFriend(String mail);
	
	public List<String> getFriendsList(String mail);

}

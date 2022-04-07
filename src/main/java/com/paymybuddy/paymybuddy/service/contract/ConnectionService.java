package com.paymybuddy.paymybuddy.service.contract;


public interface ConnectionService {

	void addAConnection(int customerId, String email);

	int getConnectionIdByCustomersId(int customerSourceId, int customerRecipientId);

	
	

//	void deleteAConnection(int customerId, String email);

}

package com.paymybuddy.paymybuddy.dao.contract;


public interface ConnectionDao {
	
	void addAConnection(int customerId, String email);

	int getConnectionIdByCustomersId(int customerSourceId, int customerRecipientId);

	void deleteAConnection(int customerSourceId, String email);

}

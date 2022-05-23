package com.paymybuddy.paymybuddy.dao.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface CustomerDao {
	
	public List<Customer> getAllCustomerRecipients(MyMainUser user);

	public Customer getCustomerRecipientNameById(Customer customer);

	public int getCustomerIdByName(Customer customer);

	public List<Customer> getAllInformationsOfCustomerById(int customerSourceId);

	void updateCustomerBalance(int customerId, int transferId);

	void updateCustomerBalanceAfterPaymentFromBankToApp(int bankOperationId);

	void updateCustomerBalanceAfterPaymentFromAppToBank(int bankOperationId);

	public void monetizationApp(int customerSourceId, int transferId);

	public void registerNewCustomerIntoDatabase(Customer customer, String encryptionPassword);

//	public int getCustomerIdByName(String email);

}

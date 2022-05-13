package com.paymybuddy.paymybuddy.dao.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.Customer;

public interface CustomerDao {
	
	public List<Customer> getAllCustomerRecipients(int customerId);

	public Customer getCustomerRecipientIdAndEmailById(int connection);

	public int getCustomerIdByEmail(String email);

	public List<Customer> getAllInformationsOfCustomerById(int customerSourceId);

	void updateCustomerBalance(int customerId, int transferId);

	void updateCustomerBalanceAfterPaymentFromBankToApp(int bankOperationId);

	void updateCustomerBalanceAfterPaymentFromAppToBank(int bankOperationId);

	public void monetizationApp(int customerSourceId, int transferId);

	public void registerNewCustomerIntoDatabase(Customer customer, String encryptionPassword);

}

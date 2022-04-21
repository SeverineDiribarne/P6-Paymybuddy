package com.paymybuddy.paymybuddy.service.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.BankTransferDisplay;
import com.paymybuddy.paymybuddy.model.Customer;

public interface HomeService {

	Customer getBalance(int customerId);

	List<BankTransferDisplay> getBankOperations(int customerId);

}

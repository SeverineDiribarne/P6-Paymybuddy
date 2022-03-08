package com.paymybuddy.paymybuddy.service.contract;

import com.paymybuddy.paymybuddy.model.Customer;

public interface HomeService {

	Customer getBalance(int customerId);

}

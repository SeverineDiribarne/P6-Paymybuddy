package com.paymybuddy.paymybuddy.dao.contract;

import com.paymybuddy.paymybuddy.model.Customer;

public interface HomeDao {

	Customer getBalance(int customerId);

}

package com.paymybuddy.paymybuddy.dao.contract;

import com.paymybuddy.paymybuddy.model.Customer;

public interface CustomerDetailsDao {

	Customer getCustomer(String email);

}
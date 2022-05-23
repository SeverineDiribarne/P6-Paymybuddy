package com.paymybuddy.paymybuddy.service.contract;


import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface HomeService {

	Customer getBalance(MyMainUser user);

}

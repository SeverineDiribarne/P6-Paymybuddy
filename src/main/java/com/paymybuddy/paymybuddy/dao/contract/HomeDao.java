package com.paymybuddy.paymybuddy.dao.contract;

import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface HomeDao {

	Customer getBalance(MyMainUser user);

	void monetizationAppFromAppToBank(int customerSourceId, int bankOperationId);

}

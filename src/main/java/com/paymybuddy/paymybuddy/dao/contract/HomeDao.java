package com.paymybuddy.paymybuddy.dao.contract;

//import java.util.List;

import com.paymybuddy.paymybuddy.model.Customer;
//import com.paymybuddy.paymybuddy.model.Transfer;

public interface HomeDao {

	Customer getBalance(int customerId);

	//List<Transfer> getTransfers(int customerId);

}

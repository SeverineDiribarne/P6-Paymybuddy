package com.paymybuddy.paymybuddy.service.contract;

import java.util.List;

import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface TransferService {

	List<Transfer> getListOfTransfers(MyMainUser user);

	void addPayment(Transfer transfer, MyMainUser user);

}

package com.paymybuddy.paymybuddy.service.contract;

import java.util.Date;
import java.util.List;
import com.paymybuddy.paymybuddy.model.Transfer;

public interface TransferService {

	List<Transfer> getTransfers(int owner);

	void addPaiement(int owner, Date date, int friend, String description, double amount);

}

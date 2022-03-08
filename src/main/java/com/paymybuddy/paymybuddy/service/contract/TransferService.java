package com.paymybuddy.paymybuddy.service.contract;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.model.TransferType;

public interface TransferService {

	List<Transfer> getTransfers(int owner);

	void addPaiement(int owner, Date date, int friend, String description, double amount);

}

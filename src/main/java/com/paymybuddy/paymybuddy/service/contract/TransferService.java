package com.paymybuddy.paymybuddy.service.contract;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.paymybuddy.paymybuddy.dto.TransferDisplay;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface TransferService {

	List<Transfer> getListOfTransfers(MyMainUser user);

	void addPayment(Transfer transfer, MyMainUser user);

	Page<TransferDisplay> getTransfersPaginated(Pageable pageable, MyMainUser user);

}
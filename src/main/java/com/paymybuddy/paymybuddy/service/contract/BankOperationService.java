package com.paymybuddy.paymybuddy.service.contract;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.paymybuddy.paymybuddy.dto.BankTransferDisplay;
import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.model.BankOperation;
import com.paymybuddy.paymybuddy.security.MyMainUser;

public interface BankOperationService {
	
	List<BankOperation> getBankOperations(MyMainUser user);

	void addPaymentFromBankToApp(BankOperation bankOperation);

	void addPaymentFromAppToBank(MyMainUser user, BankOperation bankOperation);
	
	BankOperation getLastOperationId();

	List<BankAccount> getName(BankOperation bankOperation);

	Page<BankTransferDisplay> getBankOperationsPaginated(Pageable pageable, MyMainUser user);

}

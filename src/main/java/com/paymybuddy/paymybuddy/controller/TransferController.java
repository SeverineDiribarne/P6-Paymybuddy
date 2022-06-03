package com.paymybuddy.paymybuddy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paymybuddy.paymybuddy.dto.BankTransferDisplay;
import com.paymybuddy.paymybuddy.dto.TransferDisplay;
import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.model.BankOperation;
import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.BankOperationService;
import com.paymybuddy.paymybuddy.service.contract.ConnectionService;
import com.paymybuddy.paymybuddy.service.contract.CustomerService;
import com.paymybuddy.paymybuddy.service.contract.TransferService;

@Controller
@RequestMapping ("/transfer")
public class TransferController {

	@Autowired
	private TransferService transferService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ConnectionService connectionService;

	@Autowired
	private BankOperationService bankOperationService;

	private static final String TRANSFER = "transfer";

	@GetMapping 
	public String showTransfersAndFriends(Model model,  @AuthenticationPrincipal MyMainUser user) {
		//for the arraylist of relationships'transfers 
		String customerRecipientName = "";
		List<Transfer> transfers = transferService.getListOfTransfers(user); 
		String userMainName = "";
		List<TransferDisplay> transferDisplayList = new ArrayList<>();
		for(Transfer transfer : transfers) {
			Connection connection = transfer.getConnection();
			userMainName = user.getCustomer().getFirstName() + " " + user.getCustomer().getLastName();
			Customer customerRecipient = customerService.getCustomerRecipientNameById(connection.getCustomerRecipient());
			customerRecipientName = customerRecipient.getFirstName() + " " + customerRecipient.getLastName();	
			if (transfer.getAmount()>=0) {
				String temp = "";
				temp = userMainName;
				userMainName = customerRecipientName;
				customerRecipientName = temp;	
			}
			TransferDisplay transferDisplay = new TransferDisplay(transfer.getDate(),userMainName, customerRecipientName, transfer.getDescription(), transfer.getAmount());
			transferDisplayList.add(transferDisplay);			
		}
		//For the arraylist of bank transfers
		List<BankOperation> bankOperations = bankOperationService.getBankOperations(user);
		List<BankTransferDisplay> bankTransferDisplayList = new ArrayList<>();
		for(BankOperation bankOperation : bankOperations) {
			if(bankOperation.getDescription().equals("Payment from Bank to App")) {
				List<BankAccount> sourceName =  bankOperationService.getName(bankOperation);
				String recipientName = user.getCustomer().getFirstName() + " " + user.getCustomer().getLastName();
				BankTransferDisplay bankTransferDisplay = new BankTransferDisplay(bankOperation.getDate(),sourceName.get(0).getBankAccountName(), recipientName, bankOperation.getDescription(), bankOperation.getBankOperationAmount());
				bankTransferDisplayList.add(bankTransferDisplay);
			}
			else {
				String sourceName =  user.getCustomer().getFirstName() + " " + user.getCustomer().getLastName();
				List<BankAccount> recipientName = bankOperationService.getName(bankOperation);
				BankTransferDisplay bankTransferDisplay = new BankTransferDisplay(bankOperation.getDate(),sourceName, recipientName.get(0).getBankAccountName(), bankOperation.getDescription(), bankOperation.getBankOperationAmount());
				bankTransferDisplayList.add(bankTransferDisplay);
			}
		}
		//for drop-down list of relationships
		List<Customer> customers =  customerService.getAllCustomerRecipients(user);
		List<Connection> connections = new ArrayList<>();
		for(Customer customer : customers) {
			int connectionId = connectionService.getConnectionIdByCustomersIdWithMainUser(user, customer);
			Connection connection = new Connection(connectionId, user, customer);
			connections.add(connection);
		}
		model.addAttribute("transferDisplayList", transferDisplayList);
		model.addAttribute("bankTransferDisplayList", bankTransferDisplayList);
		model.addAttribute("username", user.getCustomer().getFirstName());
		model.addAttribute("connections", connections);
		model.addAttribute(TRANSFER, new Transfer());
		return TRANSFER;
	}

	@PostMapping
	public String addPayment(Model model, @AuthenticationPrincipal MyMainUser user, @ModelAttribute Transfer transfer) {
		transferService.addPayment(transfer,user);
		List<Transfer> transfers = transferService.getListOfTransfers(user);
		List<TransferDisplay> transferDisplayList = new ArrayList<>();
		for(Transfer transferOfTheList : transfers) {
			Customer customerSource = customerService.getCustomerRecipientNameById(transferOfTheList.getConnection().getCustomerSource());
			Customer customerRecipient = customerService.getCustomerRecipientNameById(transferOfTheList.getConnection().getCustomerRecipient());
			String customerSourceName = customerSource.getFirstName() + " " + customerSource.getLastName();
			String customerRecipientName = customerRecipient.getFirstName() + " " + customerRecipient.getLastName();
			TransferDisplay transferDisplay = new TransferDisplay(transferOfTheList.getDate(), customerSourceName, customerRecipientName, transferOfTheList.getDescription(), transferOfTheList.getAmount());
			transferDisplayList.add(transferDisplay);			
		}
		showTransfersAndFriends( model, user);
		model.addAttribute( "transferDisplayList", transferDisplayList);
		model.addAttribute("username", user.getCustomer().getFirstName());
		return TRANSFER;
	}
}
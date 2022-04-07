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

import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.model.TransferDisplay;
import com.paymybuddy.paymybuddy.security.MyMainUser;
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

	private static final String TRANSFER = "transfer";

	@GetMapping 
	public String showTransfersAndFriends(Model model,  @AuthenticationPrincipal MyMainUser user) {
		List<Transfer> transfers = transferService.getListOfTransfers(user.getCustomer().getCustomerId()); 
		List<TransferDisplay> transferDisplayList = new ArrayList<>();
		for(Transfer transfer : transfers) {
			Connection connection = transfer.getConnection();
			Customer customerRecipient = customerService.getCustomerRecipientIdAndEmailById(connection.getCustomerRecipient().getCustomerId());
			TransferDisplay transferDisplay = new TransferDisplay(transfer.getDate(), customerRecipient.getEmail(), transfer.getDescription(), transfer.getAmount());
			transferDisplayList.add(transferDisplay);			
		}
		model.addAttribute( "transferDisplayList", transferDisplayList);
		model.addAttribute("username", user.getCustomer().getFirstName());
		List<Customer> customers =  customerService.getAllCustomerRecipients(user.getCustomer().getCustomerId());
		List<Connection> connections = new ArrayList<>();
		for(Customer customer : customers) {
			Connection connection = new Connection(customer);
			connections.add(connection);
		}
		model.addAttribute("connections", connections);
		model.addAttribute(TRANSFER, new Transfer());
		return TRANSFER;
	}

	@PostMapping
	public String addPayment(Model model, @AuthenticationPrincipal MyMainUser user, @ModelAttribute Transfer transfer) {
		Connection connection = new Connection();
		connection.setCustomerSource(user.getCustomer());
		connection.setCustomerRecipient(transfer.getConnection().getCustomerRecipient());
		int customerRecipientId = customerService.getCustomerIdByEmail(connection.getCustomerRecipient().getEmail());
		connection.getCustomerRecipient().setCustomerId(customerRecipientId); 
		connection.setConnectionId(connectionService.getConnectionIdByCustomersId(user.getCustomer().getCustomerId(), customerRecipientId));
		
		transferService.addPayment(transfer.getDate(),connection.getCustomerSource().getCustomerId(), connection.getCustomerRecipient().getCustomerId(), transfer.getDescription(), transfer.getAmount());
		List<Transfer> transfers = transferService.getListOfTransfers(user.getCustomer().getCustomerId());
		List<TransferDisplay> transferDisplayList = new ArrayList<>();
		for(Transfer transferOfTheList : transfers) {
			TransferDisplay transferDisplay = new TransferDisplay(transferOfTheList.getDate(), transferOfTheList.getConnection().getCustomerRecipient().getEmail(), transferOfTheList.getDescription(), transferOfTheList.getAmount());
			transferDisplayList.add(transferDisplay);			
		}
		model.addAttribute( "transferDisplayList", transferDisplayList);
		model.addAttribute("username", user.getCustomer().getFirstName());
		return TRANSFER;
	}
}

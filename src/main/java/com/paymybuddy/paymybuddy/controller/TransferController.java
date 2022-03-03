package com.paymybuddy.paymybuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.CustomerService;
import com.paymybuddy.paymybuddy.service.contract.TransferService;

@Controller
@RequestMapping ("/transfer")
public class TransferController {

	@Autowired
	private TransferService transferService;
	
	@Autowired
	private CustomerService customerService;
	
	private static final String TRANSFER = "transfer";

	@GetMapping 
	public String showTransfersAndFriends(Model model,  @AuthenticationPrincipal MyMainUser user) {

		List<Transfer> transfers = transferService.getTransfers(user.getCustomer().getCustomerId());
		model.addAttribute( "transfers", transfers);
		model.addAttribute("username", user.getCustomer().getFirstName());
		List<Customer> customers =  customerService.getAllFriends(user.getCustomer().getCustomerId());
		model.addAttribute("customers", customers);
		model.addAttribute(TRANSFER, new Transfer());
		return TRANSFER;
	}

	@PostMapping
	public String addPaiement(Model model, @AuthenticationPrincipal MyMainUser user, @ModelAttribute Transfer transfer) {
		transferService.addPaiement(user.getCustomer().getCustomerId(), transfer.getDate(),Integer.parseInt(transfer.getFriend()), transfer.getDescription(),transfer.getAmount());
		List<Transfer> transfers = transferService.getTransfers(user.getCustomer().getCustomerId());
		model.addAttribute( "transfers", transfers);
		model.addAttribute("username", user.getCustomer().getFirstName());
		
		return TRANSFER;
	}
}

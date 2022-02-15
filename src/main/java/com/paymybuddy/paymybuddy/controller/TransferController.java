package com.paymybuddy.paymybuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.TransferService;

@Controller
@RequestMapping ("/transfer")
public class TransferController {

	@Autowired
	private TransferService transferService;
	
	private static final String TRANSFER = "transfer";

	@GetMapping 
	public String showTransfers(Model model,  @AuthenticationPrincipal MyMainUser user) {
		
		List<Transfer> transfers = transferService.getTransfers(user.getCustomer().getCustomerId());
		model.addAttribute( TRANSFER, transfers);
		model.addAttribute("username", user.getCustomer().getFirstName());
		return TRANSFER;
	}

//	@PostMapping("/pay")
//	public String addTransfer(@ModelAttribute String connection , @ModelAttribute String description, @ModelAttribute double amount ) {
//		transferService.addTransfer(connection, description, amount);
//		return TRANSFER;
//	}
//	
//	@PostMapping("/connection")
//	public String addAConnection( @ModelAttribute String email) {
//		transferService.addAConnection(email);
//		return TRANSFER;
//	}
//	
//	@DeleteMapping
//	public String deleteAConnection(@ModelAttribute String email) {
//		transferService.deleteAConnection(email);
//		return TRANSFER;
//	}
//	
//	@GetMapping ("/friend")
//	public List<Customer> getFriendList(){
//		return transferService.getFriendsList();
//	}

}

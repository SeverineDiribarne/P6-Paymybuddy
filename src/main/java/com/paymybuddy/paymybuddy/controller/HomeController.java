package com.paymybuddy.paymybuddy.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paymybuddy.paymybuddy.model.BankOperation;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.BankAccountService;
import com.paymybuddy.paymybuddy.service.contract.HomeService;
import com.paymybuddy.paymybuddy.service.contract.TransferService;


@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private TransferService transferService;
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@GetMapping 
	public String showbalance(Model model,  @AuthenticationPrincipal MyMainUser user) {

		Customer customer = homeService.getBalance(user.getCustomer().getCustomerId());
		double balance = customer.getBalance();
		model.addAttribute( "balance", balance);
		model.addAttribute("username", user.getCustomer().getFirstName());
		return "home";
	}
	
	@PostMapping("/paymentOnApp")
	public String getPaymentFromBankToApp(Model model, @AuthenticationPrincipal MyMainUser user, @ModelAttribute Transfer transfer) {
		java.util.Date transferDate = Calendar.getInstance().getTime();
		transfer.setDate(transferDate);
		String iban = user.getCustomer().getBankAccount().getIban();
		transfer.setFriend(iban);
		String transferDescription = "Payment from bank to App";
		transfer.setDescription(transferDescription);
		bankAccountService.addWidrawalFromBank(bankAccountService.getBankAccountId(iban), transferDate, user.getCustomer().getCustomerId(), transferDescription , transfer.getAmount());
		List<Transfer> transfers = homeService.getTransfers(user.getCustomer().getCustomerId());
		model.addAttribute( "transfers", transfers);
		List<BankOperation> operations = bankAccountService.getBankOperations();
		model.addAttribute("operations", operations);
		model.addAttribute("username", user.getCustomer().getFirstName());
		return "redirect:/transfer";
	}
	
	@PostMapping("/paymentOnBank")
	public String getPaymentFromAppToBank(Model model, @AuthenticationPrincipal MyMainUser user, @ModelAttribute Transfer transfer) {
		
		
		return "redirect:/home";
	}
}

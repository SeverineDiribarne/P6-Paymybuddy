package com.paymybuddy.paymybuddy.controller;

import java.util.ArrayList;
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

import com.paymybuddy.paymybuddy.dto.BankTransferDisplay;
import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.model.BankOperation;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.BankAccountService;
import com.paymybuddy.paymybuddy.service.contract.BankOperationService;
import com.paymybuddy.paymybuddy.service.contract.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private HomeService homeService;

	@Autowired
	private BankOperationService bankOperationService;

	@Autowired
	private BankAccountService bankAccountService;
	
	private static final String USERNAME = "username";

	@GetMapping 
	public String showbalance(Model model,  @AuthenticationPrincipal MyMainUser user) {
		Customer customer = homeService.getBalance(user);
		double balance = customer.getBalance();
		model.addAttribute("bankOperation", new BankOperation());
		model.addAttribute( "balance", balance);
		model.addAttribute(USERNAME, user.getCustomer().getFirstName());
		return "home";
	}

	@PostMapping("/paymentOnApp")
	public String addPaymentFromBankToApp(Model model, @AuthenticationPrincipal MyMainUser user, @ModelAttribute BankOperation bankOperation) {
		java.util.Date date = Calendar.getInstance().getTime();
		bankOperation.setDate(date);
		String description = "Payment from bank to App";
		bankOperation.setDescription(description);
		bankOperation.setSource(bankAccountService.getBankAccountId(user));
		bankOperation.setRecipient(user.getCustomer().getCustomerId());
		bankOperationService.addPaymentFromBankToApp(bankOperation);
		List<BankOperation> operations = bankOperationService.getBankOperations(user);
		List<BankTransferDisplay> bankTransferDisplayList = new ArrayList<>();
		for(BankOperation operationOfTheList : operations) {
			List<BankAccount> bankAccountList = bankAccountService.getAllElementsOfBankAccount(user);
			String sourceName = bankAccountList.get(0).getBankAccountName();
			String recipientName = user.getCustomer().getFirstName() + " " + user.getCustomer().getLastName();
			BankTransferDisplay bankTransferDisplay = new BankTransferDisplay(operationOfTheList.getDate(),sourceName, recipientName, operationOfTheList.getDescription(), operationOfTheList.getBankOperationAmount());
			bankTransferDisplayList.add(bankTransferDisplay);			
		}
		model.addAttribute("bankTransferDisplayList", bankTransferDisplayList);
		model.addAttribute(USERNAME, user.getCustomer().getFirstName());
		return "redirect:/home";
	}

	@PostMapping("/paymentOnBank")
	public String getPaymentFromAppToBank(Model model, @AuthenticationPrincipal MyMainUser user, @ModelAttribute BankOperation bankOperation) {
		java.util.Date date = Calendar.getInstance().getTime();
		bankOperation.setDate(date);
		String description = "Payment from App to Bank";
		bankOperation.setDescription(description);
		bankOperation.setSource(user.getCustomer().getCustomerId());
		bankOperation.setRecipient(bankAccountService.getBankAccountId(user));
		bankOperationService.addPaymentFromAppToBank(bankOperation);
		List<BankOperation> operations = bankOperationService.getBankOperations(user);
		List<BankTransferDisplay> bankTransferDisplayList = new ArrayList<>();
		for(BankOperation operationOfTheList : operations) {
			List<BankAccount> bankAccountList = bankAccountService.getAllElementsOfBankAccount(user);
			String sourceName = user.getCustomer().getFirstName() + " " + user.getCustomer().getLastName();
			String recipientName = bankAccountList.get(0).getBankAccountName();
			BankTransferDisplay bankTransferDisplay = new BankTransferDisplay(operationOfTheList.getDate(),sourceName, recipientName, operationOfTheList.getDescription(), operationOfTheList.getBankOperationAmount());
			bankTransferDisplayList.add(bankTransferDisplay);			
		}
		model.addAttribute("bankTransferDisplayList", bankTransferDisplayList);
		model.addAttribute(USERNAME, user.getCustomer().getFirstName());

		return "redirect:/home";
	}
}

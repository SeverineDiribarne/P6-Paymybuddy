package com.paymybuddy.paymybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.HomeService;


@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	
	
	@GetMapping 
	public String showbalance(Model model,  @AuthenticationPrincipal MyMainUser user) {

		Customer customer = homeService.getBalance(user.getCustomer().getCustomerId());
		double balance = customer.getBalance();
		model.addAttribute( "balance", balance);
		model.addAttribute("username", user.getCustomer().getFirstName());
		return "home";
	}
	
	@PostMapping("/paymentOnApp")
	public String getPaymentFromBankToApp(Model model, @AuthenticationPrincipal MyMainUser user) {
		
		
		return "redirect:/home";
	}
	
	@PostMapping("/paymentOnBank")
	public String getPaymentFromAppToBank(Model model, @AuthenticationPrincipal MyMainUser user) {
		
		
		return "redirect:/home";
	}
}

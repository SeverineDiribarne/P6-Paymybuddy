package com.paymybuddy.paymybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paymybuddy.paymybuddy.model.Account;
import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.service.contract.CustomerService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private static final String REDIRECT_LOGIN = "redirect:/login";

	@Autowired
	CustomerService customerService;

	/**
	 * get register
	 * @param model
	 * @return register page
	 */
	@GetMapping
	public String getRegister(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("account", new Account());
		model.addAttribute("bankAccount", new BankAccount());
		return "register";
	}

	/**
	 * register new Customer into database
	 * @param customer
	 * @return login page
	 */
	@PostMapping
	public String registerNewCustomerIntoDatabase(@ModelAttribute Customer customer ) {
		if( !customer.getFirstName().equals("") && !customer.getLastName().equals("") &&
				!customer.getEmail().equals("") && !customer.getAccount().getPassword().equals("") &&
				!customer.getBankAccount().getBankAccountName().equals("") && !customer.getBankAccount().getIban().equals("") &&
				!customer.getBankAccount().getBic().equals("") && !customer.getBankAccount().getSwift().equals("")) {
			customerService.registerNewCustomerIntoDatabase(customer);
		}
		return REDIRECT_LOGIN;
	}
}
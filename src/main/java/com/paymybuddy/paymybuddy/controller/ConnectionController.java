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
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.ConnectionService;
import com.paymybuddy.paymybuddy.service.contract.CustomerService;

@Controller
@RequestMapping ("/connection")
public class ConnectionController {

	@Autowired
	ConnectionService connectionService;
	@Autowired
	CustomerService customerService;
	
	
	@GetMapping
	public String getConnection(Model model) {
		model.addAttribute("customer", new Customer());
		return "connection";
	}
	
	@PostMapping("/add")
	public String addAConnection(Model model,  @AuthenticationPrincipal MyMainUser user, @ModelAttribute Customer customer) {
		connectionService.addAConnection(user.getCustomer().getCustomerId(), customer.getEmail());
		List<Customer> customers =  customerService.getAllCustomerRecipients(user.getCustomer().getCustomerId());
		model.addAttribute("customers", customers);
		return "redirect:/transfer";
	}
	
	@PostMapping("/delete")
	public String deleteAConnection (Model model,  @AuthenticationPrincipal MyMainUser user, @ModelAttribute Customer customer) {
		connectionService.deleteAConnection(user.getCustomer().getCustomerId(), customer.getEmail());
		List<Customer> customers =  customerService.getAllCustomerRecipients(user.getCustomer().getCustomerId());
		model.addAttribute("customers", customers);
		return "redirect:/transfer";
	}
	
//	@GetMapping("/add")
//	public String addConnectionForm(Model model) {
//	  model.addAttribute("customer", new Customer());
//	  return "connection";
//	}
//	@GetMapping("/delete")
//	public String deleteConnectionForm(Model model) {
//	  model.addAttribute("customer", new Customer());
//	  return "connection";
//	}
}

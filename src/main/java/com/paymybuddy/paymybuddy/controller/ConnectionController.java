package com.paymybuddy.paymybuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
>>>>>>> feature/daoBranch

import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.ConnectionService;
import com.paymybuddy.paymybuddy.service.contract.CustomerService;

@Controller
public class ConnectionController {

	@Autowired
	ConnectionService connectionService;
	@Autowired
	CustomerService customerService;
	
	
	@GetMapping("/connection")
	public String getConnection() {
		return "connection";
	}
	
	@PostMapping("/add")
	public String addAConnection(Model model,  @AuthenticationPrincipal MyMainUser user, @ModelAttribute Customer customer) {
		connectionService.addAConnection(user.getCustomer().getCustomerId(), customer.getEmail());
		List<Customer> customers =  customerService.getAllFriends(user.getCustomer().getCustomerId());
		model.addAttribute("customers", customers);
		return "redirect:/transfer";
	}
	
//	@PostMapping("/delete")
//	public String deleteAConnection (Model model,  @AuthenticationPrincipal MyMainUser user, @ModelAttribute String email) {
//		connectionService.deleteAConnection(user.getCustomer().getCustomerId(), email);
//		List<Customer> customers =  customerService.getAllFriends(user.getCustomer().getCustomerId());
//		model.addAttribute("customers", customers);
//		return "redirect:/transfer";
//	}

}

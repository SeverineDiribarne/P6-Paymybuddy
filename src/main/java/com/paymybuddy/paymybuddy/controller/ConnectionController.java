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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping ("/connection")
public class ConnectionController {

	@Autowired
	ConnectionService connectionService;
	@Autowired
	CustomerService customerService;
	
	private static final String USERNAME = "username";
	
	private static final Logger log = LogManager.getLogger(); 


	/**
	 * get Connection
	 * @param model
	 * @param user
	 * @return connection page
	 */
	@GetMapping
	public String getConnection(Model model, @AuthenticationPrincipal MyMainUser user) {
		model.addAttribute("customer", new Customer());
		model.addAttribute(USERNAME, user.getCustomer().getFirstName());
		return "connection";
	}
	
	/**
	 * Add a connection
	 * @param model
	 * @param user
	 * @param customer
	 * @return transfer page
	 */
	@PostMapping("/add")
	public String addAConnection(Model model,  @AuthenticationPrincipal MyMainUser user, @ModelAttribute Customer customer) {
		try {
			connectionService.addAConnection(user, customer);
		} catch (Exception e) {
			log.debug("This user cannot be added");
		}
		List<Customer> customers =  customerService.getAllCustomerRecipients(user);
		model.addAttribute("customers", customers);
		return "redirect:/transfer";
	}
	
	/**
	 * delete a connection
	 * @param model
	 * @param user
	 * @param customer
	 * @return transfer page
	 */
	@PostMapping("/delete")
	public String deleteAConnection (Model model,  @AuthenticationPrincipal MyMainUser user, @ModelAttribute Customer customer) {
		try {
			connectionService.deleteAConnection(user, customer);
		} catch (Exception e) {
			log.debug("This user cannot be deleted");
		}
		List<Customer> customers =  customerService.getAllCustomerRecipients(user);
		model.addAttribute("customers", customers);
		return "redirect:/transfer";
	}
}
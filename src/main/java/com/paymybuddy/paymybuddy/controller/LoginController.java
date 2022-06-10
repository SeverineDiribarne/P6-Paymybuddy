package com.paymybuddy.paymybuddy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	/**
	 * get login
	 * @return login page
	 */
	@GetMapping
	public String getLogin() {
		return "login";
	}
}
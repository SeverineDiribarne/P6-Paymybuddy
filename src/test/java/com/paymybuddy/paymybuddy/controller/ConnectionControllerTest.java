package com.paymybuddy.paymybuddy.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

 class ConnectionControllerTest {
	@Autowired
	private ConnectionController connectionController;

	@Autowired
	private MockMvc mockMvc;

	
	
	@Test
	void getConnectionTest() {
		connectionController.getConnection();
		Assertions.assertTrue(connectionController.getConnection().contains("connection"));
	}
	
	@Test
	void addConnectionTest() {
		//GIVEN
		Model model;
		Customer mainUser = new Customer(8, "severine@gmail.com", "AZE123", "Sèverine", "Diribarne");
		MyMainUser user = new MyMainUser(mainUser);
		Customer customer = new Customer("jerome@gmail.com");
		
		//THEN
	//	mockMvc.
		
		//WHEN
	//	Assertions.assert(null);
		
		
	}
	@Test
	void getDeleteTest() {
		//GIVEN
		
		
		//WHEN
		
		
		//THEN
		
		
		
		
	}
}

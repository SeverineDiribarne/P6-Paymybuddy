package com.paymybuddy.paymybuddy.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginControllerTest {

	@Autowired
	private LoginController loginController;


	@Test
	void getLoginTest() {
		loginController.getLogin();
		Assertions.assertTrue(loginController.getLogin().contains("login"));
	}
}

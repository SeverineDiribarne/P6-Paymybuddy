package com.paymybuddy.paymybuddy.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	/**
	 * 	User Attributes
	 */
	private int customerId;
	private String lastName;
	private String firstName;
	private String email;
	private double balance;
	private BankAccount bankAccount;
	private Account account = new Account();
	private List<Customer> friends = new ArrayList<>();
	private List<Transfer> transfers = new ArrayList<>();



	/**
	 * Complete Constructor 
	 * @param id
	 * @param lastName
	 * @param firstName
	 * @param email
	 * @param balance
	 */
	public Customer( String lastName, String firstName, String email, double balance) {
		this.customerId = 0;
		this.lastName= lastName;
		this.firstName = firstName;
		this.email = email;
		this.balance = balance;
	}
	
	/**
	 * Constructor with id and password
	 * @param customerId
	 * @param email
	 * @param password
	 * @param firstName
	 * @param lastName
	 */
	public Customer(int customerId, String email, String password, String firstName, String lastName) {
		this.customerId= customerId;
		this.email = email;
		account.setPassword(password);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * constructor with email
	 * @param email
	 */
	public Customer(String email) {
		this.email = email;
	}

	/**
	 * Getter Id
	 * @return the id
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * Getter lastName
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Getter firstName
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Getter Email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Getter Balance
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Setter Balance
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the bankAccount
	 */
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	/**
	 * @param bankAccount the bankAccount to set
	 */
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @return the friends
	 */
	public List<Customer> getFriends() {
		return friends;
	}
	
	/**
	 * @return the transfers
	 */
	public List<Transfer> getTransfers() {
		return transfers;
	}


}

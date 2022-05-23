package com.paymybuddy.paymybuddy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable{

	
	private static final long serialVersionUID = 1L;
	/**
	 * 	User Attributes
	 */
	private int customerId;
	private String completeName;
	private String lastName;
	private String firstName;
	private String email;
	private double balance;
	private transient BankAccount bankAccount;
	private transient Account account;
	private List<Customer> friends = new ArrayList<>();
	private transient List<Transfer> transfers = new ArrayList<>();


	/**
	 * Empty constructor
	 */
	public Customer() {}
	
	/**
	 * Complete Constructor
	 */
	public Customer(String lastName, String firstName, String email, String password, String bankAccountName, String iban, String bic, String swift) {
		this.customerId = 0;
		this.lastName= lastName;
		this.firstName = firstName;
		this.email = email;
		this.balance = 0;
		this.account = new Account();
		this.account.setPassword(password);
		this.bankAccount = new BankAccount();
		this.bankAccount.setBankAccountName(bankAccountName);
		this.bankAccount.setIban(iban);
		this.bankAccount.setBic(bic);
		this.bankAccount.setSwift(swift);	
	}

	/**
	 * Constructor with id, lastName, firstName, email, balance and bankAccount
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
		this.bankAccount = new BankAccount();
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
		this.account = new Account();
		account.setPassword(password);
		this.firstName = firstName;
		this.lastName = lastName;
	}

//	/**
//	 * constructor with only email
//	 * @param email
//	 */
//	public Customer(String email) {
//		this.email = email;
//	}
	
	/**
	 * constructor with only completeName
	 * @param email
	 */
	public Customer(int customerId, String completeName) {
		this.customerId = customerId;
		this.completeName = completeName;
	}
	
	/**
	 * constructor pour CustomerRecipientIdAndNameRowMapper
	 * @param customerId
	 * @param firstName
	 * @param lastName
	 */
	public Customer(int customerId, String firstName, String lastName) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * constructor with only customerId
	 * @param customerId
	 */
	public Customer(int customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * Constructor with firstName and lastName
	 * @param firstName
	 * @param lastName
	 */
	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Constructor with only balance
	 * @param balance
	 */
	public Customer(double balance) {
		this.balance = balance;
	}

	
	/**
	 * Getter Id
	 * @return the id
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * Setter customerId
	 * @param customerId
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * Getter completeName
	 * @return completeName
	 */
	public String getCompleteName() {
		return completeName;
	}
	/**
	 * Setter completeName
	 * @param completeName
	 */
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}
	
	/**
	 * Getter lastName
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Setter lastName
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter firstName
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Setter firstName
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter Email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setter Email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Setter account
	 * @param account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @return the friends
	 */
	public List<Customer> getFriends() {
		return friends;
	}
	/**
	 * Setter friends
	 * @param friends
	 */

	public void setFriends(List<Customer> friends) {
		this.friends = friends;
	}

	/**
	 * @return the transfers
	 */
	public List<Transfer> getTransfers() {
		return transfers;
	}
	/**
	 * Setter transfers
	 * @param transfers
	 */
	public void setTransfers(List<Transfer> transfers) {
		this.transfers = transfers;
	}
	/**
	 * toString method
	 */
	public String toString() {
		   return  this.email;
		}
}

package com.paymybuddy.paymybuddy.model;

public class Account {

	/**
	 * Account Attributes
	 */
	private int accountId;
	private String password;
	private AccountState state;
	
	/**
	 * Constructor
	 * @param id
	 * @param password
	 * @param userId
	 * @param state
	 */
	public Account (int id, String password, AccountState state ) {
		this.accountId = id;
		this.password = password;
		this.state = state;
	}
	
	/**
	 * Empty contructor
	 */
	public Account() {
		this.accountId = 0;
		this.password = "";
		this.state = AccountState.DISABLE;
	}
	
	/**
	 * Getter Id
	 * @return the id
	 */
	public int getId() {
		return accountId;
	}
	
	
	/**
	 * Getter Password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Setter Password
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Getter State
	 * @return the state
	 */
	public AccountState getState() {
		return state;
	}
	
	/**
	 * Setter State
	 * @param state the state to set
	 */
	public void setState(AccountState state) {
		this.state = state;
	}
}

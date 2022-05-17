package com.paymybuddy.paymybuddy.model;

import java.io.Serializable;

public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * Account Attributes
	 */
	private int accountId;
	private String password;
	
	
	/**
	 * Constructor
	 * @param id
	 * @param password
	 * @param userId
	 * @param state
	 */
	public Account (int id, String password) {
		this.accountId = id;
		this.password = password;
	}
	
	/**
	 * Empty contructor
	 */
	public Account() {
		this.accountId = 0;
		this.password = "";
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
}

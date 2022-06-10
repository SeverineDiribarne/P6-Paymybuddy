package com.paymybuddy.paymybuddy.model;

public class BankAccount {

	//Attributes 
	private int bankAccountId;
	private String bankAccountName;
	private String iban;
	private String bic;
	private String swift;
	private int userId;
	
	/**
	 * Empty constructor
	 */
	public BankAccount() {}
	
	/**
	 * Complete Constructor
	 * @param bankAccountId
	 * @param bankAccountName
	 * @param iban
	 * @param bic
	 * @param swift
	 * @param userId
	 */
	public BankAccount(int bankAccountId, String bankAccountName, String iban, String bic, String swift, int userId) {
		this.bankAccountId = bankAccountId;
		this.bankAccountName = bankAccountName;
		this.iban = iban;
		this.bic = bic;
		this.swift = swift;
		this.userId = userId;
	}

	/**
	 * Constructor with bankAccountName only
	 * @param bankAccountName
	 */
	public BankAccount(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	/**
	 * @return the bankAccountId
	 */
	public int getBankAccountId() {
		return bankAccountId;
	}

	/**
	 * @param bankAccountId the bankAccountId to set
	 */
	public void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	/**
	 * @return the bankAccountName
	 */
	public String getBankAccountName() {
		return bankAccountName;
	}

	/**
	 * @param bankAccountName the bankAccountName to set
	 */
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	/**
	 * getter iban
	 * @return the iban
	 */
	public String getIban() {
		return iban;
	}
	
	/**
	 * setter iban
	 * @param iban the iban to set
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	/**
	 * getter bic
	 * @return the bic
	 */
	public String getBic() {
		return bic;
	}
	
	/**
	 * setter bic
	 * @param bic the bic to set
	 */
	public void setBic(String bic) {
		this.bic = bic;
	}
	
	/**
	 * getter swift
	 * @return the swift
	 */
	public String getSwift() {
		return swift;
	}
	
	/**
	 * setter swift
	 * @param swift the swift to set
	 */
	public void setSwift(String swift) {
		this.swift = swift;
	}
	/**
	 * getter userId
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * setter userId
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * toString method
	 */
	public String toString() {
		return  "" + this.bankAccountId
				+ this.bankAccountName
				+ this.iban
				+ this.bic
				+ this.swift
				+ this.userId;
	}
}
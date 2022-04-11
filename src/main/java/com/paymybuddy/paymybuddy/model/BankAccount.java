package com.paymybuddy.paymybuddy.model;

public class BankAccount {

	private int bankAccountId;
	private String name;
	private String iban;
	private String bic;
	private String swift;
	private int userId;

	/**
	 * @return the id
	 */
	public int getId() {
		return bankAccountId;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.bankAccountId = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the iban
	 */
	public String getIban() {
		return iban;
	}
	
	/**
	 * @param iban the iban to set
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	/**
	 * @return the bic
	 */
	public String getBic() {
		return bic;
	}
	
	/**
	 * @param bic the bic to set
	 */
	public void setBic(String bic) {
		this.bic = bic;
	}
	
	/**
	 * @return the swift
	 */
	public String getSwift() {
		return swift;
	}
	
	/**
	 * @param swift the swift to set
	 */
	public void setSwift(String swift) {
		this.swift = swift;
	}
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
}

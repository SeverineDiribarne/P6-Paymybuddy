package com.paymybuddy.paymybuddy.model;

import java.util.Date;

public class BankTransferDisplay {

	private Date date;
	private String sourceName;
	private String recipientName;
	private String description;
	private double amount;
	
	//Empty Constructor
	public BankTransferDisplay() {}

	//Complete Constructor
	public BankTransferDisplay(Date date, String sourceName, String recipientName, String description, double amount) {
		this.date = date;
		this.sourceName = sourceName;
		this.recipientName = recipientName;
		this.description = description;
		this.amount = amount;	
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	/**
	 * @return the recipientName
	 */
	public String getRecipientName() {
		return recipientName;
	}

	/**
	 * @param recipientName the recipientName to set
	 */
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * toString method
	 */
	public String toString() {
		return "" + this.date 
				+ this.sourceName
				+ this.recipientName
				+ this.description
				+ this.amount;
	}
}

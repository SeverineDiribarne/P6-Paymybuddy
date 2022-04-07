package com.paymybuddy.paymybuddy.model;

import java.util.Date;

public class TransferDisplay  {

	private Date date;
	private String email;
	private String description;
	private double amount;
	private TransferType transferType;
	
	/**
	 * Complete constructor
	 * @param date
	 * @param firstName
	 * @param lastName
	 * @param description
	 * @param amount
	 * @param transferType
	 */
	public TransferDisplay(Date date, String email, String description, double amount) {
		this.date = date;
		this.setEmail(email);
		this.description = description;
		this.amount = amount;
		if(amount <0) {
			this.transferType = TransferType.DEBIT;
			}
			else {
				this.transferType = TransferType.CREDIT;
			}
	}
	
//	/**
//	 * Empty Constructor
//	 */
//	public TransferDisplay() {}

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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the transferType
	 */
	public TransferType getTransferType() {
		return transferType;
	}
	/**
	 * @param transferType the transferType to set
	 */
	public void setTransferType(TransferType transferType) {
		this.transferType = transferType;
	}
}

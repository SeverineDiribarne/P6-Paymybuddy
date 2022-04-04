package com.paymybuddy.paymybuddy.model;

import java.util.Date;

public class TransferDisplay  {

	private Date date;
	private String firstName;
	private String lastName;
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
	public TransferDisplay(Date date, String firstName, String lastName, String description, double amount) {
		this.date = date;
		this.firstName = firstName;
		this.lastName = lastName;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

package com.paymybuddy.paymybuddy.model;

import java.util.Date;

public class BankOperation {

	//Attributes 
	private int bankOperationId;
	private Date date;
	private String description;
	private double bankOperationAmount;
	private int source;
	private int recipient;
	
	/**
	 * Empty Constructor
	 */
	public BankOperation() {}
	
	/**
	 * Complete constructor
	 * @param bankOperationId
	 * @param date
	 * @param description
	 * @param bankOperationAmount
	 * @param source
	 * @param recipient
	 */
	public BankOperation(int bankOperationId, Date date, String description, double bankOperationAmount, int source, int recipient) {
		this.bankOperationId = bankOperationId;
		this.date = date;
		this.description = description;
		this.bankOperationAmount = bankOperationAmount;
		this.source = source;
		this.recipient = recipient;
	}
	
	/**
	 * Constructor with only bankOperationId
	 * @param bankOperationId
	 */
	public BankOperation(int bankOperationId) {
		this.bankOperationId = bankOperationId;
	}
	
	/**
	 * Constructor with only bankOperationAmount
	 * @param bankOperationAmount
	 */
	public BankOperation(double bankOperationAmount) {
		this.bankOperationAmount = bankOperationAmount;
		}
	
	/**
	 * Getter bankOperationId
	 * @return the bankOperationId
	 */
	public int getBankOperationId() {
		return bankOperationId;
	}
	
	/**
	 * Setter bankOperationId
	 * @param bankOperationId the bankOperationId to set
	 */
	public void setBankOperationId(int bankOperationId) {
		this.bankOperationId = bankOperationId;
	}
	
	/**
	 * Getter date
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Setter date
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	/**
	 * Getter description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Setter description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter bankOperationAmount
	 * @return the bank operation amount
	 */
	public double getBankOperationAmount() {
		return bankOperationAmount;
	}
	
	/**
	 * Setter bankOperationAmount
	 * @param amount the amount to set
	 */
	public void setBankOperationAmount(double bankOperationAmount) {
		this.bankOperationAmount = bankOperationAmount;
	}
	
	/**
	 * Getter source
	 * @return the source
	 */
	public int getSource() {
		return source;
	}

	/**
	 * Setter source
	 * @param source the source to set
	 */
	public void setSource(int source) {
		this.source = source;
	}

	/**
	 * Getter recipient
	 * @return the recipient
	 */
	public int getRecipient() {
		return recipient;
	}

	/**
	 * Setter recipient
	 * @param recipient the recipient to set
	 */
	public void setRecipient(int recipient) {
		this.recipient = recipient;
	}

	/**
	 * toString method
	 */
	public String toString() {
		return  "" + this.bankOperationId
				+ this.date
				+ this.description
				+ this.bankOperationAmount;
	}
}

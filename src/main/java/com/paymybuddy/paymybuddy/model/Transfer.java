  package com.paymybuddy.paymybuddy.model;

public class Transfer {

	private String connection;
	private String description;
	private double amount;
	private TransferType transferType;

	/**
	 * Constructor
	 */
	public Transfer (String connection, String description, double amount) {
		this.connection = connection;
		this.description = description;
		this.amount = amount;
		if(amount <0) {
		this.transferType = TransferType.DEBIT;
		}
		else {
			this.transferType = TransferType.CREDIT;
		}
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * @return the beneficiary
	 */
	public String getConnection() {
		return connection;
	}
	
	/**
	 * @return the transferType
	 */
	public TransferType getTransferType() {
		return transferType;
	}
}

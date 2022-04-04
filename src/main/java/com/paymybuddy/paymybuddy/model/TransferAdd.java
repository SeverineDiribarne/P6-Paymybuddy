package com.paymybuddy.paymybuddy.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TransferAdd {
	
@DateTimeFormat(pattern = "yyyy-MM-dd")
	
	private Date date;
	private String name;
	private String description;
	private double amount;
	private TransferType transferType;

	/**
	 * Complete Constructor
	 */
	public TransferAdd (Date date, String name,  String description, double amount) {
		this.date = date;
		this.name = name;
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

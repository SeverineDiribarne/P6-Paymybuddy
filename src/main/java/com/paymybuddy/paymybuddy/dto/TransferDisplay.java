package com.paymybuddy.paymybuddy.dto;

import java.util.Date;

import com.paymybuddy.paymybuddy.model.TransferType;

public class TransferDisplay  {

	//Attributes
	private Date date;
	private String sourceName;
	private String recipientName;
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
	public TransferDisplay(Date date, String sourceName, String recipientName, String description, double amount) {
		this.date = date;
		this.sourceName = sourceName;
		this.recipientName = recipientName;
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
	 * Getter sourceName
	 * @return sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * Setter sourceName
	 * @param sourceName
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	/**
	 * Getter recipientName
	 * @return recipientName
	 */
	public String getRecipientName() {
		return recipientName;
	}

	/**
	 * Setter RecipientName
	 * @param recipientName
	 */
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	/**
	 * Getter Description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Setter Description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Getter Amount
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * Setter Amount
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * Getter TransferType
	 * @return the transferType
	 */
	public TransferType getTransferType() {
		return transferType;
	}
	/**
	 * Setter TransferType
	 * @param transferType the transferType to set
	 */
	public void setTransferType(TransferType transferType) {
		this.transferType = transferType;
	}
}
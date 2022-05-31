package com.paymybuddy.paymybuddy.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;


public class Transfer {

	//Attributes
	private int transferId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private Customer customerRecipient;
	private Connection connection;
	private String description;
	private double amount;
	private TransferType transferType;


	/**
	 * empty constructor
	 */
	public Transfer() {}


	/**
	 * Constructor with connectionId, description and amount
	 * @param connectionId
	 * @param description
	 * @param amount
	 */
	public Transfer (int connectionId, int connectionSource, int connectionRecipient, Date date, String description, double amount) {
		this.connection = new Connection(connectionId, connectionSource, connectionRecipient);
		this.date = date;
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
	 * constructor with only transfer id 
	 * @param transferId
	 */
	public Transfer(int transferId) {
		this.transferId = transferId;
	}
	/**
	 * Getter transferId
	 * @return transferId
	 */
	public int getTransferId() {
		return transferId;
	}

	/**
	 * Getter date
	 * @return date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Setter date
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	} 

	/**
	 * 
	 * @return
	 */
	public Customer getCustomerRecipient() {
		return customerRecipient;
	}
	/**
	 * 
	 * @param customerRecipient
	 */
	public void setCustomerRecipient(Customer customerRecipient) {
		this.customerRecipient = customerRecipient;
	}
	/**
	 * Getter connection 
	 * @return connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Setter connection 
	 * @param connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Getter description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter amount
	 * @return amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Setter amount
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Getter transfer Type
	 * @return transferType
	 */
	public TransferType getTransferType() {
		return transferType;
	}

	/**
	 * Setter transfer Type
	 * @param transferType
	 */
	public void setTransferType(TransferType transferType) {
		this.transferType = transferType;
	}

	@Override
	public String toString() {
		return "Transfer ["
				+ "transferId=" + transferId + 
				", date=" + date + 
				", customerRecipient=" + customerRecipient
				+ ", connection=" + connection + 
				", description=" + description + 
				", amount=" + amount
				+ ", transferType=" + transferType + 
				"]";
	}
}

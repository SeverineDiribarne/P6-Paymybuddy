  package com.paymybuddy.paymybuddy.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;


public class Transfer {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	
	private Date date;
	private Connection connection;
	private String description;
	private double amount;
	private TransferType transferType;

	/**
	 * Complete Constructor
	 */
	public Transfer ( Connection connection, Date date, String description, double amount) {
		this.connection = connection;
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
	
//	public Transfer(Date date, String connection, String description, double amount) {
//		 this.date = date;
//		
//		 
//		 this.description = description;
//		 this.amount = amount;
//	}

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
}

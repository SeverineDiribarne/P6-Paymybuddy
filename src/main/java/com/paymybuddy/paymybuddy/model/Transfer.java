  package com.paymybuddy.paymybuddy.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;


public class Transfer {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private String friend;
	private String description;
	private double amount;
	private TransferType transferType;

	/**
	 * Constructor
	 */
	public Transfer (Date date, String friend, String description, double amount) {
		this.date = date;
		this.friend = friend;
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
	 * 
	 * @param friend
	 * @param description
	 * @param amount
	 */
	public Transfer(String friend, String description, double amount) {
		this.friend = friend;
		this.description = description;
		this.amount = amount;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getFriend() {
		return friend;
	}
	
	public void setFriend(String friend) {
		this.friend = friend;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public TransferType getTransferType() {
		return transferType;
	}
	
	public void setTransferType(TransferType transferType) {
		this.transferType = transferType;
	}
}

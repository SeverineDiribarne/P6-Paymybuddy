  package com.paymybuddy.paymybuddy.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;


public class Transfer {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	
	private Date date;
	private Connexion connexion;
	private String description;
	private double amount;
	private TransferType transferType;

	/**
	 * Complete Constructor
	 */
	public Transfer (Date date, Connexion connexion, String description, double amount) {
		this.date = date;
		this.connexion = connexion;
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
	 * @param connexionDestinataire
	 * @param description
	 * @param amount
	 */
	public Transfer(Connexion connexion, String description, double amount) {
		this.connexion = connexion;
		this.description = description;
		this.amount = amount;
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
	 * Getter connexion 
	 * @return connexion
	 */
	public Connexion getConnexion() {
		return connexion;
	}
	
	/**
	 * Setter connexion 
	 * @param connexion
	 */
	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
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

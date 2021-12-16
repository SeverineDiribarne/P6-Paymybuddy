package com.paymybuddy.paymybuddy.model;

public class TransferType {

	private int id;
	
	public enum type {
		CREDIT, DEBIT
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}

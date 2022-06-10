package com.paymybuddy.paymybuddy.model;

public enum TransferType {

		CREDIT (1),
		DEBIT (2);

	private final int value;
	
	/***
	 * Constructor
	 * @param value
	 */
	private TransferType(int value) {
	    this.value = value;
	}

	/**
	 * getter value
	 * @return value
	 */
	public int getValue() {
	    return value;
	}
}
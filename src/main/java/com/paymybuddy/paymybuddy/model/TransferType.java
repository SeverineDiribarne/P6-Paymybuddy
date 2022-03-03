package com.paymybuddy.paymybuddy.model;

public enum TransferType {

		CREDIT (1),
		DEBIT (2);

	private final int value;
	
	private TransferType(int value) {
	    this.value = value;
	}

	public int getValue() {
	    return value;
	}
}

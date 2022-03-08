package com.paymybuddy.paymybuddy.model;

public enum AccountState {

	ENABLE (1),
	DISABLE (2);

private final int value;
	
	private AccountState(int value) {
	    this.value = value;
	}

	public int getValue() {
	    return value;
	}
}

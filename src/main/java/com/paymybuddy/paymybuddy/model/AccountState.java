package com.paymybuddy.paymybuddy.model;

public class AccountState {

	private int id;
	
	public enum state {
		ENABLE, DISABLE
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

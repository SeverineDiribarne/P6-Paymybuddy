package com.paymybuddy.paymybuddy.model;

import java.io.Serializable;

import com.paymybuddy.paymybuddy.security.MyMainUser;

public class Connection implements Serializable{

	private static final long serialVersionUID = 1L;

	//Attributes
	int connectionId;
	transient Customer  customerSource;
	transient Customer customerRecipient;

	/**
	 * Constructor with only connexionId
	 * @param connectionId
	 */
	public Connection(int connectionId) {
		this.connectionId = connectionId;
	}

	/**
	 * constructor with connectionId, connectionSource, connectionRecipient
	 * @param connectionId 
	 * @param connectionRecipient 
	 * @param connectionSource 
	 */
	public Connection(int connectionId, int connectionSource, int connectionRecipient){
		this.connectionId = connectionId;
		customerSource = new Customer(connectionSource);
		customerRecipient = new Customer(connectionRecipient);
	}

	/**
	 * Empty constructor
	 */
	public Connection() {}

	/**
	 * Constructor with Customers objects
	 * @param connectionId
	 * @param customerSource
	 * @param customerRecipient
	 */
	public Connection(int connectionId, MyMainUser user, Customer customerRecipient) {
		this.connectionId = connectionId;
		this.customerSource = user.getCustomer();
		this.customerRecipient = customerRecipient;
	}

	/**
	 * Getter Connexion Id
	 * @return connectionId
	 */
	public int getConnectionId() {
		return connectionId;
	}

	/**
	 * Setter Connexion Id
	 * @param connexionId
	 */
	public void setConnectionId(int connexionId) {
		this.connectionId = connexionId;
	}

	/**
	 * Getter Customer Source
	 * @return Customer Source
	 */
	public Customer getCustomerSource() {
		return customerSource;
	}

	/**
	 * Setter Customer Source
	 * @param customerSource
	 */
	public void setCustomerSource(Customer customerSource) {
		this.customerSource = customerSource;
	}

	/**
	 * Getter Customer Recipient
	 * @return Customer Recipient
	 */
	public Customer getCustomerRecipient() {
		return customerRecipient;
	}

	/**
	 * Setter Customer Recipient
	 * @param customerRecipient
	 */
	public void setCustomerRecipient(Customer customerRecipient) {
		this.customerRecipient = customerRecipient;
	}

	/**
	 * toString method
	 */
	public String toString() {
		return "Connection Id " + this.connectionId +
				" CustomerSource " + this.customerSource +
				" CustomerRecipient " + this.customerRecipient ;
	}
}
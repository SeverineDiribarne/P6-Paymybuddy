package com.paymybuddy.paymybuddy.model;

import java.io.Serializable;

public class Connection implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
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
	 * Constructor with customerRecipient
	 * @param customerRecipient
	 */
	public Connection(Customer customerRecipient) {
		this.customerRecipient = customerRecipient;
	}
	/**
	 * Empty constructor
	 */
	public Connection() {}
	
	/**
	 * constructor with only name
	 * @param name
	 */
	public Connection(String email) {
	 this.customerRecipient = new Customer(email); 
	}
	
	/**
	 * Getter Connexion Id
	 * @return
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

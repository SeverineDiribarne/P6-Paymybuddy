package com.paymybuddy.paymybuddy.model;

public class Connection {

	int connectionId;
	Customer customerSource;
	Customer customerRecipient;
	
	/**
	 * Constructor with only connexionId
	 * @param connectionId
	 */
	public Connection(int connectionId) {
		this.connectionId = connectionId;
	}
	/**
	 * Empty constructor
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
}

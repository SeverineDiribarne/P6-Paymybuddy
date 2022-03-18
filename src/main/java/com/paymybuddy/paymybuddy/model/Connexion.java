package com.paymybuddy.paymybuddy.model;

public class Connexion {

	int connexionId;
	Customer customerSource;
	Customer customerDestinataire;
	
	/**
	 * Getter Connexion Id
	 * @return
	 */
	public int getConnexionId() {
		return connexionId;
	}
	
	/**
	 * Setter Connexion Id
	 * @param connexionId
	 */
	public void setConnexionId(int connexionId) {
		this.connexionId = connexionId;
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
	 * Getter Customer Destinataire
	 * @return Customer Destinataire
	 */
	public Customer getCustomerDestinataire() {
		return customerDestinataire;
	}
	
	/**
	 * Setter Customer Destinataire
	 * @param customerDestinataire
	 */
	public void setCustomerDestinataire(Customer customerDestinataire) {
		this.customerDestinataire = customerDestinataire;
	}
}

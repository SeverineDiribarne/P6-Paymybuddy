package com.paymybuddy.paymybuddy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerRecipientIdAndNameRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.InformationsOfCustomerByIdRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerIdByEmailRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerIdByNameRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerIdentityRowMapper;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	private static final String GET_ALL_CUSTOMER_RECIPIENTS_QUERY = "SELECT cust.id, cust.firstName, cust.lastName, cust.email, cust.balance"
			+ " FROM customer cust "
			+ " JOIN connection con ON cust.id = con.connectionRecipient"
			+ " WHERE con.connectionSource = ?;";

	/**
	 * get Friends List 
	 * @return list of friends
	 */
	@Override
	public List<Customer> getAllCustomerRecipients(MyMainUser user) {
		return jdbcTemplate.query(GET_ALL_CUSTOMER_RECIPIENTS_QUERY, new CustomerIdentityRowMapper(), user.getCustomer().getCustomerId());
	}
	private static final String GET_CUSTOMER_ID_AND_EMAIL_QUERY = "SELECT cust.firstName, cust.lastName"
			+ " FROM customer cust"
			+ " WHERE cust.id = ?;";
	/**
	 * get CustomerRecipientName By Id
	 * @Return list of customer recipient with firstName and lastName
	 */
	public Customer getCustomerRecipientNameById (Customer customer){
		return jdbcTemplate.queryForObject(GET_CUSTOMER_ID_AND_EMAIL_QUERY, new CustomerRecipientIdAndNameRowMapper(), customer.getCustomerId());
	}

//	private static final String GET_CUSTOMER_ID_BY_CONNECTION_EMAIL_QUERY ="SELECT cust.id"
//			+ " FROM customer cust"
//			+ " WHERE cust.email = ?;";
//	/**
//	 * get CustomerId By Email
//	 */
//	@Override
//	public int getCustomerIdByEmail(Customer customer) {
//		
//		return jdbcTemplate.queryForObject(GET_CUSTOMER_ID_BY_CONNECTION_NAME_QUERY, new CustomerIdByEmailRowMapper(), customer.getEmail());
//	}

	private static final String GET_ALL_INFORMATIONS_OF_CUSTOMER_BY_ID_QUERY ="SELECT cust.firstName, cust.lastName, cust.email, cust.balance"
			+ " FROM customer cust"
			+ " WHERE cust.id = ? ;"; 
	/**
	 * Get all informations of customer by id
	 */
	@Override
	public List<Customer> getAllInformationsOfCustomerById(int customerSourceId) {
		return jdbcTemplate.query(GET_ALL_INFORMATIONS_OF_CUSTOMER_BY_ID_QUERY, new InformationsOfCustomerByIdRowMapper(), customerSourceId);
	}

	public static final String BALANCE_CALCULATION_QUERY = "UPDATE customer cust"
			+ " JOIN connection con"
			+ " ON cust.id = con.connectionSource"
			+ " JOIN transfer t "
			+ " ON con.connectionId = t.connection"
			+ " SET cust.balance = (cust.balance + t.amount)"
			+ " WHERE cust.id = ? AND t.transferId = ? ;";

	/**
	 * Update Customer balance
	 */
	@Override
	public void updateCustomerBalance(int customerId, int transferId) {
		jdbcTemplate.update(BALANCE_CALCULATION_QUERY, customerId, transferId);
	}

	public static final String BALANCE_CALCULATION_FROM_BANK_TO_APP_QUERY ="UPDATE customer cust"
			+ " JOIN bankaccount ba"
			+ " ON cust.id = ba.customer_id"
			+ " JOIN bank_operation bo"
			+ " ON ba.bankAccount_id = bo.bank_accountId"
			+ " SET cust.balance = (cust.balance + bo.operationAmount)"
			+ " WHERE bo.operationId = ?;";
	/**
	 * Update customer's balance after payment from bank to app
	 * @param bankOperationId
	 */
	@Override
	public void updateCustomerBalanceAfterPaymentFromBankToApp(int bankOperationId) {
		jdbcTemplate.update(BALANCE_CALCULATION_FROM_BANK_TO_APP_QUERY, bankOperationId);	
	}

	public static final String BALANCE_CALCULATION_FROM_APP_TO_BANK_QUERY = "UPDATE customer cust"
			+ " JOIN bankaccount ba"
			+ " ON cust.id = ba.customer_id"
			+ " JOIN bank_operation bo"
			+ " ON ba.bankAccount_id = bo.bank_accountId"
			+ " SET cust.balance = (cust.balance - bo.operationAmount)"
			+ " WHERE bo.operationId = ?;";

	/**
	 * Update customer's balance after payment from app to bank
	 */
	@Override
	public void updateCustomerBalanceAfterPaymentFromAppToBank( int bankOperationId) {
		jdbcTemplate.update(BALANCE_CALCULATION_FROM_APP_TO_BANK_QUERY, bankOperationId);
	}

	public static final String MONETIZATION_APPLICATION_TRANSFER_ON_ONLY_SOURCE = "UPDATE customer cust"
			+ " JOIN connection con"
			+ " ON cust.id = con.connectionSource"
			+ " JOIN transfer t"
			+ " ON con.connectionId = t.connection"
			+ " SET cust.balance = (cust.balance + (t.amount * 0.5 / 100))"
			+ " WHERE cust.id = ? AND t.transferId = ? ;";
	@Override
	public void monetizationApp(int customerSourceId, int transferId) {
		jdbcTemplate.update(MONETIZATION_APPLICATION_TRANSFER_ON_ONLY_SOURCE, customerSourceId, transferId);	
	}

	public static final String REGISTER_NEW_CUSTOMER_INTO_DATABASE = "INSERT INTO customer"
			+ " (lastName, firstName, email, balance)"
			+ " VALUES (?,?,?,?);";

	public static final String REGISTER_NEW_ACCOUNT_INTO_DATABASE = "INSERT INTO account"
			+ " (login, password, customer_id)"
			+ " VALUES (?,?,?);";

	public static final String REGISTER_NEW_BANK_ACCOUNT_INTO_DATABASE = "INSERT INTO bankaccount"
			+ " (bankAccountName, iban, bic, swift, customer_Id)"
			+ " VALUES (?,?,?,?,?);";
	/**
	 * register New Customer Into Database
	 */
	@Override
	public void registerNewCustomerIntoDatabase(Customer customer, String encryptionPassword) {
		double balance = 0;
		jdbcTemplate.update(REGISTER_NEW_CUSTOMER_INTO_DATABASE, customer.getLastName(), customer.getFirstName(), customer.getEmail(), balance);
		int customerId = getCustomerIdByName(customer);
		jdbcTemplate.update(REGISTER_NEW_ACCOUNT_INTO_DATABASE, customer.getEmail(), encryptionPassword, customerId);
		jdbcTemplate.update(REGISTER_NEW_BANK_ACCOUNT_INTO_DATABASE, customer.getBankAccount().getBankAccountName(), customer.getBankAccount().getIban(),
				customer.getBankAccount().getBic(), customer.getBankAccount().getSwift(), customerId);
	}

	private static final String GET_CUSTOMER_ID_BY_CUSTOMER_NAME_QUERY ="SELECT cust.id"
			+ " FROM customer cust"
			+ " WHERE cust.firstName = ? "
			+ " AND cust.lastName = ?;";
	/**
	 * get Customer Id by Name
	 * @param customer
	 * @return
	 */
	public int getCustomerIdByName(Customer customer) {
		return jdbcTemplate.queryForObject(GET_CUSTOMER_ID_BY_CUSTOMER_NAME_QUERY, new CustomerIdByNameRowMapper(),customer.getFirstName(),customer.getLastName());
	}
	
	private static final String GET_CUSTOMER_ID_BY_CUSTOMER_EMAIL_QUERY ="SELECT cust.id"
			+ " FROM customer cust"
			+ " WHERE cust.email = ?;";
	
	@Override
	public int getCustomerIdByName(String email) {
		return jdbcTemplate.queryForObject(GET_CUSTOMER_ID_BY_CUSTOMER_EMAIL_QUERY, new CustomerIdByEmailRowMapper(), email);

	}
}

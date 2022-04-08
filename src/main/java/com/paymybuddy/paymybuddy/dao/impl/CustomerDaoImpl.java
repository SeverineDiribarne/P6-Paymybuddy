package com.paymybuddy.paymybuddy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerRecipientIdAndEmailRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.InformationsOfCustomerByIdRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerIdByEmailRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerIdentityRowMapper;
import com.paymybuddy.paymybuddy.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	private static final String GET_ALL_CUSTOMER_RECIPIENTS_QUERY = "SELECT cust.id, cust.email"
			+ " FROM customer cust "
			+ " JOIN connection con ON cust.id = con.connectionRecipient"
			+ " WHERE con.connectionSource = ?;";
	/**
	 * get Friends List 
	 * @return list of friends
	 */
	@Override
	public List<Customer> getAllCustomerRecipients(int customerId) {
		return jdbcTemplate.query(GET_ALL_CUSTOMER_RECIPIENTS_QUERY, new CustomerIdentityRowMapper(), customerId);
	}
	
	private static final String GET_CUSTOMER_ID_AND_EMAIL_QUERY = "SELECT cust.id, cust.email"
			+ " FROM customer cust"
			+ " WHERE cust.id = ?;";
	/**
	 * get CustomerRecipientName By Id
	 * @Return list of customer recipient with firstName and lastName
	 */
	public Customer getCustomerRecipientIdAndEmailById (int connection){
		return jdbcTemplate.queryForObject(GET_CUSTOMER_ID_AND_EMAIL_QUERY, new CustomerRecipientIdAndEmailRowMapper(), connection);
	}
	
	private static final String GET_CUSTOMER_ID_BY_EMAIL_QUERY ="SELECT cust.id"
			+ " FROM customer cust"
			+ " WHERE email = ? ;";
	/**
	 * get CustomerId By Email
	 */
	@Override
	public int getCustomerIdByEmail(String email) {
		return jdbcTemplate.queryForObject(GET_CUSTOMER_ID_BY_EMAIL_QUERY, new CustomerIdByEmailRowMapper(), email);
	}
	
	private static final String GET_ALL_INFORMATIONS_OF_CUSTOMER_BY_ID_QUERY ="SELECT cust.firstName, cust.lastName, cust.email, cust.balance"
			+ " FROM customer cust"
			+ " WHERE cust.id = ? ;"; 
	/**
	 * 
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
			+ " SET cust.balance = (cust.balance + t.amount + (t.amount * 0.5 /100))"
			+ " WHERE cust.id = ? AND t.transferId = ? ;";
		
	/**
	 * Update Customer balance
	 */
	@Override
	public void updateCustomerBalance(int customerId, int transferId) {
		jdbcTemplate.update(BALANCE_CALCULATION_QUERY, customerId, transferId);
	}
}

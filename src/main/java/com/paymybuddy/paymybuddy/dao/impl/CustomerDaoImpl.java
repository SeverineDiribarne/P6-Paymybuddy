package com.paymybuddy.paymybuddy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerRecipientRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerIdentityRowMapper;
import com.paymybuddy.paymybuddy.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	private static final String GET_ALL_CUSTOMER_RECIPIENTS_QUERY = "SELECT cust.id, cust.firstName, cust.lastName"
			+ " FROM customer cust "
			+ " JOIN connection con ON cust.id = con.connectionRecipient"
			+ " WHERE con.connectionSource = ?;";
	/**
	 * getFriendsList Method
	 * @return list of friends
	 */
	@Override
	public List<Customer> getAllCustomerRecipients(int customerId) {
		return jdbcTemplate.query(GET_ALL_CUSTOMER_RECIPIENTS_QUERY, new CustomerIdentityRowMapper(), customerId);
	}
	
	private static final String GET_CUSTOMER_NAME_QUERY = "SELECT cust.id, cust.firstName, cust.lastName "
			+ "FROM customer cust "
			+ "WHERE cust.id = ?;";
	/**
	 * getCustomerRecipientNameById Method
	 * @Return list of customer with firstName and lastName
	 */
	public Customer getCustomerRecipientNameById (int connection){
		return jdbcTemplate.queryForObject(GET_CUSTOMER_NAME_QUERY, new CustomerRecipientRowMapper(), connection);
	}
}

package com.paymybuddy.paymybuddy.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.dao.contract.ConnectionDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.ConnectionByCustomersRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.ConnectionIdByCustomersIdRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CountFromConnectionRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerRecipientIdRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.GetRecipientCompleteNameRowMapper;
import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.security.MyMainUser;

@Repository
public class ConnectionDaoImpl implements ConnectionDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SELECT_CUSTOMER_ID_QUERY =  "SELECT cust.id FROM customer cust WHERE cust.email = ? ;"; 
	private static final String ADD_CONNECTION_QUERY =  "INSERT INTO connection (connectionSource, connectionRecipient) VALUES (?,?);"; 
	private static final String SELECT_COUNT_QUERY =  "SELECT count(*) FROM connection con WHERE con.connectionSource = ? AND con.connectionRecipient = ?;"; 

	/**
	 * Add a connection in dropdown list
	 */
	@Override
	public void addAConnection(MyMainUser user, Customer customer) {
		if(customer.getEmail()!="") {
			customer = jdbcTemplate.queryForObject(SELECT_CUSTOMER_ID_QUERY, new CustomerRecipientIdRowMapper(), customer.getEmail());
			int count = jdbcTemplate.queryForObject(SELECT_COUNT_QUERY, new CountFromConnectionRowMapper(), user.getCustomer().getCustomerId(), customer.getCustomerId());
			if(count == 0 ) {
				jdbcTemplate.update(ADD_CONNECTION_QUERY, user.getCustomer().getCustomerId(), customer.getCustomerId());
				jdbcTemplate.update(ADD_CONNECTION_QUERY, customer.getCustomerId(), user.getCustomer().getCustomerId());
			}
		}
	}

	private static final String GET_CONNECTION_ID_BY_CUSTOMERS_ID_QUERY = "SELECT con.connectionId"
			+ " FROM connection con"
			+ " WHERE con.connectionSource = ?"
			+ " AND con.connectionRecipient = ? ;";
	/**
	 * Get ConnectionId by CustomerSourceId and CustomerRecipientId
	 */
	@Override
	public int getConnectionIdByCustomersIdWithMainUser(MyMainUser user, Customer customer) {
		return jdbcTemplate.queryForObject(GET_CONNECTION_ID_BY_CUSTOMERS_ID_QUERY, new ConnectionIdByCustomersIdRowMapper(), user.getCustomer().getCustomerId(), customer.getCustomerId());
	}

	private static final String DELETE_CONNECTION_QUERY =  "DELETE FROM connection con WHERE con.connectionId = ?;"; 
	/**
	 * Delete a connection in dropdown list
	 */
	@Override
	public void deleteAConnection(MyMainUser user, Customer customer) {
		if(customer.getEmail()!="") {
			customer = jdbcTemplate.queryForObject(SELECT_CUSTOMER_ID_QUERY, new CustomerRecipientIdRowMapper(), customer.getEmail());
			if (customer.getCustomerId() != 0) {
				int connectionId = jdbcTemplate.queryForObject(GET_CONNECTION_ID_BY_CUSTOMERS_ID_QUERY, new ConnectionIdByCustomersIdRowMapper(),user.getCustomer().getCustomerId(),
						customer.getCustomerId());
				int secondConnectionId = jdbcTemplate.queryForObject(GET_CONNECTION_ID_BY_CUSTOMERS_ID_QUERY, new ConnectionIdByCustomersIdRowMapper(), customer.getCustomerId(), 
						user.getCustomer().getCustomerId());
				jdbcTemplate.update(DELETE_CONNECTION_QUERY, connectionId);
				jdbcTemplate.update(DELETE_CONNECTION_QUERY, secondConnectionId);
			}
		}
	}
	@Override
	public int getConnectionIdWithCustomersIdByConnection(double amount,Customer customerSource, Customer customerRecipient) {
		if (amount <= 0) {
			return jdbcTemplate.queryForObject(GET_CONNECTION_ID_BY_CUSTOMERS_ID_QUERY, new ConnectionIdByCustomersIdRowMapper(), customerSource.getCustomerId(), customerRecipient.getCustomerId());
		}
		else {
			return jdbcTemplate.queryForObject(GET_CONNECTION_ID_BY_CUSTOMERS_ID_QUERY, new ConnectionIdByCustomersIdRowMapper(), customerSource.getCustomerId(), customerRecipient.getCustomerId());
		}
	}
	
	private static final String GET_COMPLETE_NAME_OF_RECIPIENT_BY_HIS_CONNECTION_ID_QUERY = "select cust.firstName, cust.lastName"
			+ " FROM customer cust"
			+ " WHERE cust.id = ?;";
	
	@Override
	public Customer getRecipientNameByRecipientId(Connection connection) {
		return jdbcTemplate.queryForObject(GET_COMPLETE_NAME_OF_RECIPIENT_BY_HIS_CONNECTION_ID_QUERY, new GetRecipientCompleteNameRowMapper() , connection.getCustomerRecipient().getCustomerId());
	}
	
	private static final String GET_CONNECTION_BY_CUSTOMERS_QUERY = "select con.connectionId"
			+ " FROM connection con"
			//+ " JOIN customer cust"
			//+ " ON cust.id  = con.connectionSource"
			+ " WHERE con.connectionSource = ?"
			+ " AND con.connectionRecipient= ?;";
	
	@Override
	public Connection getConnectionByCustomers(Customer customerSource, Customer customerRecipient) {
		
		return jdbcTemplate.queryForObject(GET_CONNECTION_BY_CUSTOMERS_QUERY, new ConnectionByCustomersRowMapper(), customerSource.getCustomerId(), customerRecipient.getCustomerId());
	}

}

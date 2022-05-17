package com.paymybuddy.paymybuddy.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.dao.contract.ConnectionDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.ConnectionIdByCustomersIdRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CountFromConnectionRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerRecipientIdRowMapper;
import com.paymybuddy.paymybuddy.model.Customer;

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
	public void addAConnection(int customerSourceId, String email) {
<<<<<<< HEAD
		Customer customerRecipient = jdbcTemplate.queryForObject(SELECT_FRIEND_ID_QUERY, new CustomerRecipientIdRowMapper(), email);
		jdbcTemplate.update(ADD_CONNECTION_QUERY, customerSourceId, customerRecipient.getCustomerId() );	
=======
		Customer customerRecipient = jdbcTemplate.queryForObject(SELECT_CUSTOMER_ID_QUERY, new CustomerRecipientIdRowMapper(), email);
		int count = jdbcTemplate.queryForObject(SELECT_COUNT_QUERY, new CountFromConnectionRowMapper(), customerSourceId, customerRecipient.getCustomerId());
		if(count == 0 ) {
		jdbcTemplate.update(ADD_CONNECTION_QUERY, customerSourceId, customerRecipient.getCustomerId());	
		}
>>>>>>> feature/connection
	}

	private static final String GET_CONNECTION_ID_BY_CUSTOMERS_ID_QUERY = "SELECT con.connectionId"
			+ " FROM connection con"
			+ " WHERE con.connectionSource = ?"
			+ " AND con.connectionRecipient = ? ;";
	/**
	 * Get ConnectionId by CustomerSourceId and CustomerRecipientId
	 */
	@Override
	public int getConnectionIdByCustomersId(int customerSourceId, int customerRecipientId) {
		return jdbcTemplate.queryForObject(GET_CONNECTION_ID_BY_CUSTOMERS_ID_QUERY, new ConnectionIdByCustomersIdRowMapper(), customerSourceId, customerRecipientId);
	}


	private static final String DELETE_CONNECTION_QUERY =  "DELETE FROM connection con WHERE con.connectionId = ?;"; 
	/**
	 * Delete a connection in dropdown list
	 */
	@Override
	public void deleteAConnection(int customerSourceId, String email) {
		Customer customerRecipient = jdbcTemplate.queryForObject(SELECT_CUSTOMER_ID_QUERY, new CustomerRecipientIdRowMapper(), email);
		if (customerRecipient.getCustomerId()!= 0) {
		int connectionId = jdbcTemplate.queryForObject(GET_CONNECTION_ID_BY_CUSTOMERS_ID_QUERY, new ConnectionIdByCustomersIdRowMapper(),customerSourceId, customerRecipient.getCustomerId());
		jdbcTemplate.update(DELETE_CONNECTION_QUERY, connectionId);
		}
	}

}

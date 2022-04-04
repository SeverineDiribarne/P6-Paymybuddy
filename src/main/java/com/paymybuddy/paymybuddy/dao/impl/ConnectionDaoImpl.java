package com.paymybuddy.paymybuddy.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.dao.contract.ConnectionDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.FriendIdRowMapper;
import com.paymybuddy.paymybuddy.model.Customer;

@Repository
public class ConnectionDaoImpl implements ConnectionDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String SELECT_FRIEND_ID_QUERY =  "SELECT c.id FROM customer c WHERE c.email = ? ;"; 

	private static final String ADD_CONNECTION_QUERY =  "INSERT INTO friend (customer_id_user, customer_id_friend) VALUES (?,?);"; 

	@Override
	public void addAConnection(int customerId, String email) {
		Customer friendId = jdbcTemplate.queryForObject(SELECT_FRIEND_ID_QUERY, new FriendIdRowMapper(), email );
		jdbcTemplate.update(ADD_CONNECTION_QUERY, customerId, friendId );	
	}



//	private static final String DELETE_CONNECTION_QUERY =  "DELETE FROM friend WHERE customer_id_user = ? AND customer_id_friend = ?;"; 
//
//	@Override
//	public void deleteAConnection(int customerId, String email) {
//		Customer friendId = jdbcTemplate.queryForObject(SELECT_FRIEND_ID_QUERY, new FriendIdRowMapper(), email );
//		jdbcTemplate.update(DELETE_CONNECTION_QUERY, customerId, friendId );
//	
//	}

}

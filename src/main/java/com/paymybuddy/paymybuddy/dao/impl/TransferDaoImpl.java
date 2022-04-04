package com.paymybuddy.paymybuddy.dao.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.dao.contract.TransferDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.TransferRowMapper;
import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.model.TransferType;

@Repository
public class TransferDaoImpl implements TransferDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	private static final String GET_TRANSFERS_QUERY =  
			"SELECT con.connectionId, con.connectionSource, con.connectionRecipient, t.transferDate, t.description, t.amount "
			+ "FROM transfer t "
			+ "JOIN connection con ON t.connection = con.connectionId "
			+ "JOIN customer cust ON con.connectionSource = cust.id "
			+ "WHERE cust.id = ?;";
	/**
	 * Get all transfers in the list
	 */
	@Override
	public List<Transfer> getListOfTransfers(int mainUserId) {
	 return jdbcTemplate.query(GET_TRANSFERS_QUERY, new TransferRowMapper(), mainUserId);	
	}
	
	private static final String INSERT_TRANSFER = "INSERT INTO transfer "
			+ "(transferDate, connection, description, amount, transfer_type)"
			+ " VALUES (?,?,?,?,?);" ;
	/**
	 * add a payment in database
	 */
	@Override
	public void addPayment(Date date, Connection connection, String description, double amount) {
		jdbcTemplate.update(INSERT_TRANSFER, date, connection, description, amount, (amount < 0) ? TransferType.DEBIT.getValue() : TransferType.CREDIT.getValue());	 
	}
	

	//private static final String FIND_USER_NAME_BY_EMAIL = "SELECT c.firstName, c.lastName FROM customer c WHERE c.email = ?;";
	//	
	//	public void addAConnection(int customerId, String email) {
	//		Customer friend = jdbcTemplate.queryForObject(FIND_USER_NAME_BY_EMAIL, new UserNameRowMapper(),email);
	//		
	//	}


	//	
	//	private static final String DELETE_FRIEND_INFORMATION_QUERY = "DELETE FROM friend WHERE customer_id1 = :friendId";
	//
	//	@Override
	//	public void deleteAConnection(String email) {
	//		MapSqlParameterSource paramSource = new MapSqlParameterSource();
	//		paramSource.addValue("friendId", findFriendId(email));
	//		jdbcTemplate.execute(DELETE_FRIEND_INFORMATION_QUERY);
	//	}
	//
	//	private static final String GET_FRIENDS_LIST_DONATE_QUERY =  "SELECT  customer.firstName, customer.lastName FROM customer customer JOIN friend friend  ON customer.id = friend.customer_id1 WHERE friend.customer_id = ?;";
	//	private static final String GET_FRIENDS_LIST_RECEIVE_QUERY =  "SELECT  customer.firstName, customer.lastName FROM friend friend JOIN customer customer  ON friend.customer_id = customer.id  WHERE friend.customer_id1 = ?;";
	//
	//	@Override
	//	public List<Customer> getFriendsList() {
	//		return null;
	//	}
}

package com.paymybuddy.paymybuddy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.dao.contract.TransferDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.TransferRowMapper;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.model.TransferType;

@Repository
public class TransferDaoImpl implements TransferDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	private static final String GET_TRANSFERS_QUERY =  "SELECT customer.firstName, customer.lastName, transfer.description, transfer.amount FROM transfer transfer JOIN customer customer ON transfer.connection = customer.id ;"; //WHERE customer.id = :customerId

	@Override
	public List<Transfer> getTransfers(int customerId) {
//		MapSqlParameterSource paramSource = new MapSqlParameterSource();
//		paramSource.addValue("customerId", customerId);
		return jdbcTemplate.query(GET_TRANSFERS_QUERY, new TransferRowMapper());
	}


//	private static final String INSERT_TRANSFER = "INSERT INTO transfer (connection, description, amount) VALUES (?,?,?);" ;
//
//	@Override
//	public void addTransfer(String connection, String description, double amount) {
//		jdbcTemplate.update(
//				INSERT_TRANSFER,
//				connection, description, amount, (amount < 0 ) ? TransferType.DEBIT : TransferType.CREDIT 
//				);	
//	}
//
//	
//	
//	private static final String FIND_CUSTOMER_EMAIL = "SELECT customer.id FROM customer customer  WHERE email = ?;";
//
//	@Override
//	public int findFriendId(String email) {
//	 return Integer.parseInt(FIND_CUSTOMER_EMAIL);
//	}
//
//	
//	
//	private static final String GET_FRIEND_INFORMATION_QUERY = "SELECT customer.firstName, customer.lastName FROM customer customer WHERE email = ?;";
//	
//	@Override
//	public List<Customer> addAConnection(String email) {
//		return jdbcTemplate.query(GET_FRIEND_INFORMATION_QUERY, new CustomerRowMapper());
//	}
//
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

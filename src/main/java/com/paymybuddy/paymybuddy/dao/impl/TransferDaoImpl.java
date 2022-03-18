package com.paymybuddy.paymybuddy.dao.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.dao.contract.TransferDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.TransferRowMapper;
import com.paymybuddy.paymybuddy.model.Connexion;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.model.TransferType;

@Repository
public class TransferDaoImpl implements TransferDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Get all transfers in the list
	 */
	private static final String GET_TRANSFERS_QUERY =  "SELECT cust.firstName, cust.lastName, t.description, t.amount, t.transfer_type FROM transfer t JOIN connexion con ON t.connexion = con.connexionId JOIN customer cus ON con.ConnexionSource = cus.id JOIN customer cust ON con.ConnexionDestinataire = cust.id WHERE con.ConnexionSource = ?;"; 
	@Override
	public List<Transfer> getTransfers(int customerId) {
		return jdbcTemplate.query(GET_TRANSFERS_QUERY, new TransferRowMapper(), customerId);
	}
	/**
	 * add a payment in database
	 */
	private static final String INSERT_TRANSFER = "INSERT INTO transfer ( transferDate, connexion, description, amount, transfer_type) VALUES (?,?,?,?,?);" ;
	@Override
	public void addPayment(Date date, Connexion connexion, String description, double amount) {
		jdbcTemplate.update(INSERT_TRANSFER, date, connexion, description, amount, (amount < 0) ? TransferType.DEBIT.getValue() : TransferType.CREDIT.getValue());	 
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

package com.paymybuddy.paymybuddy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.dao.contract.CustomerDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.FriendsRowMapper;
import com.paymybuddy.paymybuddy.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	private static final String GET_ALL_FRIENDS_QUERY = "SELECT c.id, c.firstName, c.lastName FROM customer c JOIN friend f ON c.id = f.customer_id_friend WHERE f.customer_id_user = ? UNION SELECT c.id, c.firstName, c.lastName FROM friend f JOIN customer c  ON f.customer_id_user = c.id  WHERE f.customer_id_friend = ?;";
	/**
	 * getFriendsList Method
	 * @return list of friends
	 */
	@Override
	public List<Customer> getAllFriends(int customerId) {
		return jdbcTemplate.query(GET_ALL_FRIENDS_QUERY, new FriendsRowMapper(), customerId, customerId);
	}
}

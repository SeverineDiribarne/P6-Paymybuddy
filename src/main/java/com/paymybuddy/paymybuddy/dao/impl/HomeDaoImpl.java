package com.paymybuddy.paymybuddy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.paymybuddy.paymybuddy.dao.contract.HomeDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.BalanceRowMapper;
import com.paymybuddy.paymybuddy.model.Customer;


@Repository
public class HomeDaoImpl implements HomeDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String GET_BALANCE_QUERY ="SELECT balance FROM customer WHERE id = ?;";
	
	@Override
	public Customer getBalance(int customerId) {
		return jdbcTemplate.queryForObject(GET_BALANCE_QUERY, new BalanceRowMapper(), customerId);	
	}
}

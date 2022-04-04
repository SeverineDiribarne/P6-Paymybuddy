package com.paymybuddy.paymybuddy.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.dao.contract.CustomerDetailsDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.CustomerDetailsMapper;
import com.paymybuddy.paymybuddy.model.Customer;
@Repository
public class CustomerDetailsDaoImpl implements CustomerDetailsDao {

	private static final String FIND_BY_EMAIL = "SELECT cust.id, cust.email, acc.password, cust.firstName, cust.lastName FROM customer cust JOIN account acc ON cust.id = acc.customer_id WHERE cust.email  = :email;";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Customer getCustomer(String email) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("email", email);
		return namedParameterJdbcTemplate.query(FIND_BY_EMAIL, namedParameters, new CustomerDetailsMapper()).get(0);
	}
}

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
	/**
	 * Get balance
	 */
	@Override
	public Customer getBalance(int customerId) {
		return jdbcTemplate.queryForObject(GET_BALANCE_QUERY, new BalanceRowMapper(), customerId);	
	}
	 
	public static final String MONETIZATION_APPLICATION_BANK_OPERATION_FROM_SOURCE_TO_RECIPIENT_ON_ONLY_SOURCE = "UPDATE customer cust"
			+ " JOIN  bank_operation bo"
			+ " ON cust.id = bo.customerId"
			+ " SET cust.balance = (cust.balance - (bo.operationAmount * 0.5 / 100))"
			+ " WHERE cust.id = ? AND bo.operationId  = ?;";
	/**
	 * Monetization 0.5% for application From Application To bank method
	 */
	@Override
	public void monetizationAppFromAppToBank(int sourceId, int bankOperationId) {
		jdbcTemplate.update(MONETIZATION_APPLICATION_BANK_OPERATION_FROM_SOURCE_TO_RECIPIENT_ON_ONLY_SOURCE, sourceId, bankOperationId);	
	}
}

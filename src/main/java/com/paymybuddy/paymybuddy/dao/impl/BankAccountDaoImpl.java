package com.paymybuddy.paymybuddy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.paymybuddy.paymybuddy.dao.contract.BankAccountDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.BankAccountAllElementsRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.BankIdRowMapper;
import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.security.MyMainUser;


@Repository
public class BankAccountDaoImpl implements BankAccountDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String SELECT_BANK_ID_QUERY = "SELECT b.bankAccount_id FROM bankaccount b"
			+ " JOIN customer cust"
			+ " ON cust.id = b.customer_id"
			+ " WHERE cust.id = ?;"; 
	/**
	 * get bank account id
	 * @param customerId
	 * @return bankAccountId
	 */
	@Override
	public int getBankAccountId(MyMainUser user) {
	return jdbcTemplate.queryForObject(SELECT_BANK_ID_QUERY, new BankIdRowMapper(), user.getCustomer().getCustomerId());	
	}
	
	private static final String GET_BANK_ACCOUNT_ELEMENTS_QUERY = "SELECT ba.bankAccount_id, ba.bankAccountName, ba.iban, ba.bic, ba.swift, ba.customer_id"
			+ " FROM bankaccount ba"
			+ " JOIN customer cust"
			+ " ON cust.id = ba.customer_id"
			+ " WHERE cust.id = ?;";
	
	@Override
	public List<BankAccount> getAllElementsOfBankAccount(MyMainUser user) {
		return jdbcTemplate.query(GET_BANK_ACCOUNT_ELEMENTS_QUERY, new BankAccountAllElementsRowMapper(), user.getCustomer().getCustomerId());
	}
}

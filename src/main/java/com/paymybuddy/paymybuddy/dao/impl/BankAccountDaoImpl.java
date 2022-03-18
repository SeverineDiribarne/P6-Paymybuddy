package com.paymybuddy.paymybuddy.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.paymybuddy.paymybuddy.dao.contract.BankAccountDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.BankIdRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.FriendIdRowMapper;
import com.paymybuddy.paymybuddy.model.Customer;

@Repository
public class BankAccountDaoImpl implements BankAccountDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String SELECT_BANK_ID_QUERY =  "SELECT b.id FROM bank_account b WHERE b.iban = ? ;"; 
	
	@Override
	public int getBankAccountId(String iban) {
	return jdbcTemplate.queryForObject(SELECT_BANK_ID_QUERY, new BankIdRowMapper(), iban);
		
	}
	
	
}

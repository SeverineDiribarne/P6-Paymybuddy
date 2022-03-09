package com.paymybuddy.paymybuddy.dao;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.paymybuddy.paymybuddy.dao.impl.mapper.TransferRowMapper;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;

class TransferDaoImplTest {

	@Mock
	JdbcTemplate jdbcTemplate;
	
	TransferRowMapper transferRowMapper;
	Customer customer = new Customer(1);
	
	
	private static final String GET_TRANSFERS_QUERY =  "SELECT c.firstName, c.lastName, t.description, t.amount, t.transfer_type FROM transfer t JOIN customer c ON t.friend = c.id WHERE t.owner = ?;";

	@Test
	void whenMockJdbcTemplate_thenReturnAListOfTransfers() {
		Transfer transfer = new Transfer();
		ReflectionTestUtils.setField(transfer, "jdbcTemplate", jdbcTemplate);
//		Mockito.when(jdbcTemplate.query(GET_TRANSFERS_QUERY, transferRowMapper.mapRow(null, 0),customer)).th

	}
}

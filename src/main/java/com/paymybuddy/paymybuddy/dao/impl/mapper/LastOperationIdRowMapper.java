package com.paymybuddy.paymybuddy.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.paymybuddy.paymybuddy.model.BankOperation;

public class LastOperationIdRowMapper implements RowMapper<BankOperation> {

	/**
	 * retrieves the information of the operationId in the database
	 */
	@Override
	public BankOperation mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new BankOperation(rs.getInt("operationId"));
	}
}
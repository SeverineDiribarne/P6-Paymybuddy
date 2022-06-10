package com.paymybuddy.paymybuddy.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.paymybuddy.paymybuddy.model.BankOperation;

public class BankOperationsRowMapper implements RowMapper<BankOperation> {

	/**
	 * retrieves the information of the operationId, operationDate, operationDescription, operationAmount, customer_Id and bank_accountId in the database
	 */
	@Override
	public BankOperation mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new BankOperation (rs.getInt("operationId"), rs.getDate("operationDate"), rs.getString("operationDescription"), rs.getDouble("operationAmount"), rs.getInt("customer_Id"), rs.getInt("bank_accountId"));
	}
}
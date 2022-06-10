package com.paymybuddy.paymybuddy.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.paymybuddy.paymybuddy.model.BankAccount;

public class BankAccountAllElementsRowMapper implements RowMapper<BankAccount> {

	/**
	 * retrieves the information of the bankAccount_id, bankAccountName, iban, bic, swift and customer_id in the database
	 */
	@Override
	public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new BankAccount (rs.getInt("bankAccount_id"), rs.getString("bankAccountName"), rs.getString("iban"), rs.getString("bic"), rs.getString("swift"), rs.getInt("customer_id"));
	}
}
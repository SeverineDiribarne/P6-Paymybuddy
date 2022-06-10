package com.paymybuddy.paymybuddy.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ConnectionRecipientIdRowMapper implements RowMapper<Integer> {

	/**
	 * retrieves the information of the connectionRecipientId in the database
	 */
	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Integer.valueOf(rs.getInt("connectionRecipient"));
	}
}
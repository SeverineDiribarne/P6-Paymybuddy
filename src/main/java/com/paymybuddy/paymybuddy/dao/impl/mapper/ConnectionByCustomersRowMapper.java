package com.paymybuddy.paymybuddy.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.paymybuddy.paymybuddy.model.Connection;

public class ConnectionByCustomersRowMapper implements RowMapper<Connection> {

	/**
	 * retrieves the information of the connectionId in the database
	 */
	@Override
	public Connection mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Connection (rs.getInt("connectionId"));
	}
}
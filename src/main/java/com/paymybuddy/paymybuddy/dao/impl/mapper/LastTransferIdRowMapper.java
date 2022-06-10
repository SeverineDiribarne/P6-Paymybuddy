package com.paymybuddy.paymybuddy.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.paymybuddy.paymybuddy.model.Transfer;

public class LastTransferIdRowMapper implements RowMapper<Transfer> {

	/**
	 * retrieves the information of the transferId in the database
	 */
	@Override
	public Transfer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Transfer (rs.getInt("transferId"));
	}
}
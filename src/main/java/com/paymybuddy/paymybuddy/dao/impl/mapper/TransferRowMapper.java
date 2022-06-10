package com.paymybuddy.paymybuddy.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.paymybuddy.paymybuddy.dao.contract.ConnectionDao;
import com.paymybuddy.paymybuddy.model.Transfer;

public class TransferRowMapper  implements RowMapper<Transfer>{

	@Autowired
	ConnectionDao connectionDaoImpl;
	
	/**
	 * retrieves the information of the connectionId, connectionSource, connectionRecipient, transferDate, description and amount in the database
	 */
	@Override
	public Transfer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Transfer(rs.getInt("connectionId"), rs.getInt("connectionSource"), rs.getInt("connectionRecipient"), rs.getDate("transferDate"), rs.getString("description") , rs.getDouble("amount"));
		}
	}
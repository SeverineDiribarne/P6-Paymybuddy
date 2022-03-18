package com.paymybuddy.paymybuddy.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.paymybuddy.paymybuddy.model.Connexion;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;

public class TransferRowMapper  implements RowMapper<Transfer>{

	@Override
	public Transfer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Transfer( rs.getString("firstName")+ " " + rs.getString("lastName"), rs.getString("description") , rs.getDouble("amount"));
	}

	
}

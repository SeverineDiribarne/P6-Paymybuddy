package com.paymybuddy.paymybuddy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.paymybuddy.paymybuddy.dao.contract.HomeDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.BalanceRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.TransferRowMapper;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.model.TransferType;


@Repository
public class HomeDaoImpl implements HomeDao{

	@Autowired
	TransferDaoImpl transferDaoImpl;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String GET_BALANCE_QUERY ="SELECT balance FROM customer WHERE id = ?;";
	
	@Override
	public Customer getBalance(int customerId) {
		return jdbcTemplate.queryForObject(GET_BALANCE_QUERY, new BalanceRowMapper(), customerId);	
	}

	private static final String INSERT_TRANSFER = "INSERT INTO transfer (friend, transferDate, owner, description, amount, transfer_type) VALUES (?,?,?,?,?,?);" ;
	@Override
	public int addPaiement(int customerId, Object setDate, Object setFriend, Object setDescription, double amount) {
		return jdbcTemplate.update(INSERT_TRANSFER, setFriend, setDate, customerId, setDescription, amount, (amount < 0) ? TransferType.DEBIT.getValue() : TransferType.CREDIT.getValue());	
	}

//	@Override
//	public List<Transfer> getTransfers(int customerId) {
//		return transferDaoImpl.getTransfers(customerId);
//	}
}

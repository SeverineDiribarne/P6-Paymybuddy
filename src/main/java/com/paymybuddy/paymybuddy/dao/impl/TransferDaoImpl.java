package com.paymybuddy.paymybuddy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.dao.contract.TransferDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.LastTransferIdRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.TransferRowMapper;
import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.model.TransferType;
import com.paymybuddy.paymybuddy.security.MyMainUser;

@Repository
public class TransferDaoImpl implements TransferDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	private static final String GET_TRANSFERS_QUERY =  
			"SELECT t.transferDate, con.connectionId, con.connectionSource, con.connectionRecipient, t.description, t.amount"
			+ " FROM transfer t"
			+ " JOIN connection con ON t.connection = con.connectionId"
			+ " JOIN customer cust ON con.connectionSource = cust.id"
			+ " WHERE cust.id = ?"
			+ " ORDER BY t.transferId;";
	/**
	 * Get all transfers in the list
	 * @param user
	 * @return list of transfers
	 */
	@Override
	public List<Transfer> getListOfTransfers(MyMainUser user) {
		return jdbcTemplate.query(GET_TRANSFERS_QUERY, new TransferRowMapper(), user.getCustomer().getCustomerId());	
	}

	private static final String INSERT_TRANSFER = "INSERT INTO transfer "
			+ "(transferDate, connection, description, amount, transfer_type)"
			+ " VALUES (?,?,?,?,?);" ;
	/**
	 * add a payment in database
	 * @param transfer
	 * @param connection
	 * @param amount
	 */
	@Override
	public void addPayment(Transfer transfer, Connection connection, double amount) {
		jdbcTemplate.update(INSERT_TRANSFER, transfer.getDate(), connection.getConnectionId(), transfer.getDescription(), amount, (amount < 0) ? TransferType.DEBIT.getValue() : TransferType.CREDIT.getValue());	 
	}

	public static final String GET_LAST_TRANSFER_ID_QUERY = "select max(t.transferId) AS transferId  FROM transfer t;";
	/**
	 * Get Last TransferId
	 * @return last transferId
	 */
	@Override
	public Transfer getLastTransferId() {
		return jdbcTemplate.queryForObject(GET_LAST_TRANSFER_ID_QUERY, new LastTransferIdRowMapper());
	}
	
	public static final String FIND_TRANSFERS_QUERY = " ";
	
	
}
package com.paymybuddy.paymybuddy.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.paymybuddy.paymybuddy.dao.contract.BankOperationDao;
import com.paymybuddy.paymybuddy.dao.impl.mapper.BankAccountNameRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.BankOperationsRowMapper;
import com.paymybuddy.paymybuddy.dao.impl.mapper.LastOperationIdRowMapper;
import com.paymybuddy.paymybuddy.model.BankAccount;
import com.paymybuddy.paymybuddy.model.BankOperation;

@Repository
public class BankOperationDaoImpl implements BankOperationDao {

	@Autowired
	JdbcTemplate jdbcTemplate; 
	
	private static final String GET_BANK_OPERATIONS_QUERY = "SELECT bo.operationId, bo.operationDate, bo.operationDescription, bo.operationAmount, bo.customerId, bo.bank_accountId FROM bank_operation bo"
			+ " WHERE bo.customerId = ?;";
	/**
	 * Get all bank operations 
	 */
	@Override
	public List<BankOperation> getBankOperations(int customerId) {
		return jdbcTemplate.query(GET_BANK_OPERATIONS_QUERY,new BankOperationsRowMapper(), customerId);
	}
	
	private static final String ADD_PAYMENT_FROM_BANK_TO_APP_QUERY = "INSERT INTO bank_operation"
			+ " (operationDate, operationDescription, operationAmount, customerId, bank_accountId)"
			+ " VALUES (?,?,?,?,?);";
	/**
	 * Add a payment from bank to application
	 */
	@Override
	public void addPaymentFromBankToApp(Date date, String description, double amount, int source, int recipient) {
		jdbcTemplate.update(ADD_PAYMENT_FROM_BANK_TO_APP_QUERY,date,description, amount, source, recipient);
	}
	
	private static final String ADD_PAYMENT_FROM_APP_TO_BANK_QUERY = "INSERT INTO bank_operation"
			+ " (operationDate, operationDescription, operationAmount, customerId, bank_accountId)"
			+ " VALUES (?,?,?,?,?);";
	/**
	 * Add a payment from application to bank
	 */
	@Override
	public void addPaymentFromAppToBank(Date date, String description, double bankOperationAmount, int source,
			int recipient) {
		jdbcTemplate.update(ADD_PAYMENT_FROM_APP_TO_BANK_QUERY,date,description, bankOperationAmount, source, recipient);
	}	
	
	public static final String GET_LAST_OPERATION_ID_QUERY = "SELECT max(bo.operationId) AS operationId FROM bank_operation bo;";
	/**
	 * get last operation id
	 */
	@Override
	public BankOperation getLastOperationId() {
		return jdbcTemplate.queryForObject(GET_LAST_OPERATION_ID_QUERY, new LastOperationIdRowMapper());
	}
	
	public static final String GET_BANK_ACCOUNT_NAME_QUERY = "SELECT ba.bank_accountName FROM bank_account ba"
			+ " JOIN bank_operation bo"
			+ " ON bo.bank_accountId = ba.bankAccount_id"
			+ " WHERE bo.bank_accountId = ?; ";
	
	@Override
	public List<BankAccount> getName(int id) {
		return jdbcTemplate.query(GET_BANK_ACCOUNT_NAME_QUERY,new BankAccountNameRowMapper(), id);
	}
}

package com.paymybuddy.paymybuddy.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.paymybuddy.paymybuddy.dao.contract.TransferDao;
import com.paymybuddy.paymybuddy.dao.impl.TransferDaoImpl;
import com.paymybuddy.paymybuddy.helpers.DataTestsHelpers;
import com.paymybuddy.paymybuddy.model.Transfer;

class TransferDaoImplTest {

	@Mock
	JdbcTemplate jdbcTemplate;
	
	
	
	private static final String GET_TRANSFERS_QUERY =  "SELECT c.firstName, c.lastName, t.description, t.amount, t.transfer_type FROM transfer t JOIN customer c ON t.friend = c.id WHERE t.owner = 1;";

	@Test
	void whenMockJdbcTemplate_thenReturnAListOfTransfers() {
		TransferDao transferDao = new TransferDaoImpl();
		ReflectionTestUtils.setField(transferDao, "jdbcTemplate", jdbcTemplate);
		Mockito.when(jdbcTemplate.queryForObject(GET_TRANSFERS_QUERY,Transfer.class)).thenReturn(
				(Transfer) DataTestsHelpers.creationTransfersForMock());
				

		List<Transfer> transfers = transferDao.getListOfTransfers(1)	;	
		Assertions.assertEquals("Jane", transfers.get(0).getConnection().getCustomerRecipient().getFirstName());
		Assertions.assertEquals("Doe", transfers.get(0).getConnection().getCustomerRecipient().getLastName());
		Assertions.assertEquals( "Patisserie",transfers.get(0).getDescription());
		Assertions.assertEquals(33.50, transfers.get(0).getAmount());
		
		Assertions.assertEquals("Marie", transfers.get(1).getConnection().getCustomerRecipient().getFirstName());
		Assertions.assertEquals("Curie", transfers.get(1).getConnection().getCustomerRecipient().getLastName());
		Assertions.assertEquals("patinoire",transfers.get(1).getDescription());
		Assertions.assertEquals(10.80, transfers.get(1).getAmount());
		
		Assertions.assertEquals("Kira", transfers.get(2).getConnection().getCustomerRecipient().getFirstName());
		Assertions.assertEquals("Alliant", transfers.get(2).getConnection().getCustomerRecipient().getLastName());
		Assertions.assertEquals( "Cinema",transfers.get(2).getDescription());
		Assertions.assertEquals(23.40, transfers.get(2).getAmount());

	}
	
	
}

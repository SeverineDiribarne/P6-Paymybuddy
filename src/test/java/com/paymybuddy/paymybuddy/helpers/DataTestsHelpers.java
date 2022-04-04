package com.paymybuddy.paymybuddy.helpers;

import java.util.Arrays;
import java.util.List;

import com.paymybuddy.paymybuddy.model.Transfer;

public class DataTestsHelpers {

	
		/**
		 * Creation of 3 transfers for mock 
		 * @return mockTransfersList
		 */
		public static List<Transfer> creationTransfersForMock() {
			Transfer transfer1  = new Transfer();
			transfer1.getConnection().getCustomerRecipient().setFirstName("Jane");
			transfer1.getConnection().getCustomerRecipient().setLastName("Doe");
			transfer1.setDescription("Patisserie");
			transfer1.setAmount(33.50);
			

			Transfer transfer2 = new Transfer();
			transfer1.getConnection().getCustomerRecipient().setFirstName("Marie");
			transfer1.getConnection().getCustomerRecipient().setLastName("Curie");
			transfer2.setDescription("patinoire");
			transfer2.setAmount(10.80);
			
			
			Transfer transfer3 = new Transfer();
			transfer1.getConnection().getCustomerRecipient().setFirstName("Kira");
			transfer1.getConnection().getCustomerRecipient().setLastName("Alliant");
			transfer3.setDescription("Cinema");
			transfer3.setAmount(23.40);
			
			List<Transfer> mockTransfersList = Arrays.asList(transfer1, transfer2, transfer3);
			return mockTransfersList;
		}

}

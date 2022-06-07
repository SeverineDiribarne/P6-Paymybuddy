package com.paymybuddy.paymybuddy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.paymybuddy.dto.BankTransferDisplay;
import com.paymybuddy.paymybuddy.dto.TransferDisplay;
import com.paymybuddy.paymybuddy.model.Connection;
import com.paymybuddy.paymybuddy.model.Customer;
import com.paymybuddy.paymybuddy.model.Transfer;
import com.paymybuddy.paymybuddy.security.MyMainUser;
import com.paymybuddy.paymybuddy.service.contract.BankOperationService;
import com.paymybuddy.paymybuddy.service.contract.ConnectionService;
import com.paymybuddy.paymybuddy.service.contract.CustomerService;
import com.paymybuddy.paymybuddy.service.contract.TransferService;

@Controller
@RequestMapping ("/transfer")
public class TransferController {

	@Autowired
	private TransferService transferService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ConnectionService connectionService;

	@Autowired
	private BankOperationService bankOperationService;

	private static final String TRANSFER = "transfer";
	private static final String PAGE_NUMBERS = "pageNumbers";
	private static final String BANK_PAGE_NUMBERS = "bankPageNumbers";

	@GetMapping
	public String showTransfersAndFriends(Model model,  @AuthenticationPrincipal MyMainUser user,
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size,
			@RequestParam("bankPage") Optional<Integer> bankPage,
			@RequestParam("bankSize") Optional<Integer> bankSize) {
		int currentPage = (page.orElse(1))-1;
		int pageSize = size.orElse(3);

		//for the arraylist of relationships'transfers 
		Page<TransferDisplay> transferDisplayListPage = transferService.getTransfersPaginated(PageRequest.of(currentPage, pageSize), user); 

		int transferTotalPages = transferDisplayListPage.getTotalPages();
		if (transferTotalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, transferTotalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute(PAGE_NUMBERS, pageNumbers);
		}

		//For the arraylist of bank transfers
		int bankCurrentPage = (bankPage.orElse(1))-1;
		int bankPageSize = bankSize.orElse(3);
		Page<BankTransferDisplay> bankOperationsDisplayListPage = bankOperationService.getBankOperationsPaginated(PageRequest.of(bankCurrentPage, bankPageSize), user); 

		int bankTotalPages = bankOperationsDisplayListPage.getTotalPages();
		if (bankTotalPages > 0) {
			List<Integer> bankPageNumber = IntStream.rangeClosed(1, bankTotalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute(BANK_PAGE_NUMBERS, bankPageNumber);
;		}

		//for drop-down list of relationships
		List<Customer> customers =  customerService.getAllCustomerRecipients(user);
		List<Connection> connections = new ArrayList<>();
		for(Customer customer : customers) {
			int connectionId = connectionService.getConnectionIdByCustomersIdWithMainUser(user, customer);
			Connection connection = new Connection(connectionId, user, customer);
			connections.add(connection);
		}

		model.addAttribute("currentPage", currentPage + 1);
		model.addAttribute("transferDisplayListPage", transferDisplayListPage);
		model.addAttribute("bankCurrentPage", bankCurrentPage + 1);
		model.addAttribute("bankOperationsDisplayListPage", bankOperationsDisplayListPage);
		model.addAttribute("username", user.getCustomer().getFirstName());
		model.addAttribute("connections", connections);
		model.addAttribute(TRANSFER, new Transfer());
		return TRANSFER;
	}

	@PostMapping
	public String addPayment(Model model, @AuthenticationPrincipal MyMainUser user, @ModelAttribute Transfer transfer, @RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);
		transferService.addPayment(transfer,user);
		Page<TransferDisplay> transferDisplayListPage = transferService.getTransfersPaginated(PageRequest.of(currentPage - 1, pageSize), user);

		int totalPages = transferDisplayListPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute(PAGE_NUMBERS, pageNumbers);
		}
		showTransfersAndFriends( model, user, page, size, page, size);
		model.addAttribute("transferDisplayListPage", transferDisplayListPage);
		model.addAttribute("username", user.getCustomer().getFirstName());
		return TRANSFER;
	}
}
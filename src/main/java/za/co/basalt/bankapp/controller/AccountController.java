package za.co.basalt.bankapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import za.co.basalt.bankapp.domain.AccountInformation;
import za.co.basalt.bankapp.domain.TransactionDetails;
import za.co.basalt.bankapp.domain.TransferDetails;
import za.co.basalt.bankapp.service.BankingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("accounts")
@Api(tags = { "Accounts and Transactions REST endpoints" })
public class AccountController {

	private final BankingService bankingService;

	public AccountController(BankingService bankingService) {
		this.bankingService = bankingService;
	}

	@GetMapping(path = "/{accountNumber}")
	@ApiOperation(value = "Get account details", notes = "Find account details by account number")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<Object> getByAccountNumber(@PathVariable Long accountNumber) {

		return bankingService.findByAccountNumber(accountNumber);
	}

	@PostMapping(path = "/add/{customerNumber}")
	@ApiOperation(value = "Add a new account", notes = "Create an new account for existing customer.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Object> addNewAccount(@RequestBody AccountInformation accountInformation,
			@PathVariable Long customerNumber) {

		return bankingService.addNewAccount(accountInformation, customerNumber);
	}

	@PutMapping(path = "/transfer/{customerNumber}")
	@ApiOperation(value = "Transfer funds between accounts", notes = "Transfer funds between accounts.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<Object> transferDetails(@RequestBody TransferDetails transferDetails,
			@PathVariable Long customerNumber) {

		return bankingService.transferDetails(transferDetails, customerNumber);
	}

	@GetMapping(path = "/transactions/{accountNumber}")
	@ApiOperation(value = "Get all transactions", notes = "Get all Transactions by account number")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public List<TransactionDetails> getTransactionByAccountNumber(@PathVariable Long accountNumber) {

		return bankingService.findTransactionsByAccountNumber(accountNumber);
	}

	@GetMapping(path = "/transactions/{accountNumber}/{accountType}")
	@ApiOperation(value = "Get all transactions by account type", notes = "Get all Transactions by account number and account type")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public List<TransactionDetails> getTransactionByAccountNumberAndAccountType(@PathVariable Long accountNumber, @PathVariable String accountType) {

		return bankingService.findTransactionByAccountNumberAndAccountType(accountNumber, accountType);
	}


	@PutMapping(path = "/credit/{customerNumber}/{accountNumber}")
	@ApiOperation(value = "Credit an account", notes = "Add.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<Object> creditAccount(@RequestParam double amount,
												  @PathVariable Long customerNumber, @PathVariable Long accountNumber) {

		return bankingService.creditAccount(amount, customerNumber, accountNumber);
	}


	@PutMapping(path = "/debit/{customerNumber}/{accountNumber}")
	@ApiOperation(value = "debit an account", notes = "Add.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<Object> debitAccount(@RequestParam double amount,
												@PathVariable Long customerNumber, @PathVariable Long accountNumber) {

		return bankingService.debitAccount(amount, customerNumber, accountNumber);
	}
}

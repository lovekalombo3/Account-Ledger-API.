package za.co.basalt.bankapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.basalt.bankapp.domain.AccountInformation;
import za.co.basalt.bankapp.domain.CustomerDetails;
import za.co.basalt.bankapp.domain.TransactionDetails;
import za.co.basalt.bankapp.domain.TransferDetails;

@Service
public interface BankingService {

    public List<CustomerDetails> findAll();
    
    public ResponseEntity<Object> addCustomer(CustomerDetails customerDetails);
    
    public CustomerDetails findByCustomerNumber(Long customerNumber);
    
    public ResponseEntity<Object> updateCustomer(CustomerDetails customerDetails, Long customerNumber);
    
    public ResponseEntity<Object> deleteCustomer(Long customerNumber) ;
    
    public ResponseEntity<Object> findByAccountNumber(Long accountNumber);
    
    public ResponseEntity<Object> addNewAccount(AccountInformation accountInformation, Long customerNumber);
    
    public ResponseEntity<Object> transferDetails(TransferDetails transferDetails, Long customerNumber);
    
    public List<TransactionDetails> findTransactionsByAccountNumber(Long accountNumber);

    public List<TransactionDetails> findTransactionByAccountNumberAndAccountType(Long accountNumber, String accountType);
    public ResponseEntity<Object> creditAccount( double amount,
                                                Long customerNumber, Long accountNumber);

    public ResponseEntity<Object> debitAccount(double amount,
                                                Long customerNumber, Long accountNumber);



}

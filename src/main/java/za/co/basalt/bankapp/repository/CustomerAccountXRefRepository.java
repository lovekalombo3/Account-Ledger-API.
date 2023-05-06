package za.co.basalt.bankapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.basalt.bankapp.model.Account;
import za.co.basalt.bankapp.model.CustomerAccountXRef;

import java.util.Optional;

@Repository
public interface CustomerAccountXRefRepository extends CrudRepository<CustomerAccountXRef, String> {
    Optional<CustomerAccountXRef> findByAccountNumberAndCustomerNumber(Long accountNumber, Long customerNumber);
}

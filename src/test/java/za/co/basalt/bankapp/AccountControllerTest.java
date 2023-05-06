package za.co.basalt.bankapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.basalt.bankapp.domain.*;
import za.co.basalt.bankapp.repository.AccountRepository;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @LocalServerPort
    private int port;

    @LocalServerPort
    private int port2;

    private String baseUrl, baseUrl2;

    @Before
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/bank-api/accounts";
        baseUrl2 = "http://localhost:" + port2 + "/bank-api/customers";
    }

    @After
    public void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    public void testAddAccount_BAD_REQUEST() {

        CustomerDetails customerDetails = new CustomerDetails("Benjamin","Kalombo","K",1002L
                ,"Active", new AddressDetails("44 Hoffmann", "Hartbeespoort", "Ifafi","Gauteng","0215","South Africa"),
                new ContactDetails("benjamin.kalombo34000@outlook.co.za","0114334933","0123456789"));
        ResponseEntity<Void> response = restTemplate.postForEntity(
                baseUrl2 + "/add"  , customerDetails, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

        AccountInformation accountInformation = new AccountInformation(5001L,
                new BankInformation("North gate", 2001, new AddressDetails("44 Hoffmann", "Hartbeespoort", "Ifafi","Gauteng","0215","South Africa"),
                        11111),
                "Active","Current",200.45,new Date());

        response = restTemplate.postForEntity(
                baseUrl + "/add/1002L"  , accountInformation, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}

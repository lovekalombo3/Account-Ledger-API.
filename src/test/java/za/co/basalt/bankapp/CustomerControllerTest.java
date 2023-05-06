package za.co.basalt.bankapp;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.basalt.bankapp.domain.AddressDetails;
import za.co.basalt.bankapp.domain.ContactDetails;
import za.co.basalt.bankapp.domain.CustomerDetails;
import za.co.basalt.bankapp.repository.CustomerRepository;
import za.co.basalt.bankapp.service.helper.BankingServiceHelper;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/bank-api/customers"; //http://localhost:8989/bank-api/customers/add
    }

    @After
    public void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    public void testAddCustomer() {
        CustomerDetails customerDetails = new CustomerDetails("Benjamin","Kalombo","K",1002L
                ,"Active", new AddressDetails("44 Hoffmann", "Hartbeespoort", "Ifafi","Gauteng","0215","South Africa"),
                new ContactDetails("benjamin.kalombo34000@outlook.co.za","0114334933","0123456789"));
        ResponseEntity<Void> response = restTemplate.postForEntity(
                baseUrl + "/add"  , customerDetails, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

}

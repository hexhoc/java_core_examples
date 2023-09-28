package com.example.customerservice.repository;

import java.util.ArrayList;

import com.example.customerservice.TestDataFactory;
import com.example.customerservice.config.DatabaseContainerTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles(value = "postgrestest")
class CustomerRepositoryContainerTest extends DatabaseContainerTest {

    @Autowired
    private CustomerRepository customerRepository;

    private TestDataFactory testDataFactory = new TestDataFactory();

    @Test
    @DisplayName("test customer repository injection")
    void injectedComponentsAreNotNull(){
        assertThat(customerRepository).isNotNull();
    }

    @Test
    @DisplayName("test customer repository save and find by id")
    void whenSaved_thenFindsById() {
        var customer = testDataFactory.getCustomer();
        customerRepository.save(customer);
        assertThat(customerRepository.findById(1)).isNotNull();
    }
}
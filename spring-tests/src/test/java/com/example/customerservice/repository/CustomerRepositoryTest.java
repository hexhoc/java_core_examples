package com.example.customerservice.repository;

import java.util.ArrayList;

import com.example.customerservice.TestDataFactory;
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
// Using test profile, we disable flyway and use jpa to create and drop all entity
// flyway use dialect that not supported by H2 database
@ActiveProfiles(value = "test")
class CustomerRepositoryTest {

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
        customer.setContacts(new ArrayList<>());

        customerRepository.save(customer);
        assertThat(customerRepository.findById(1)).isNotNull();
    }
}
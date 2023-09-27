package com.example.customerservice;

import com.example.customerservice.TestDataFactory;
import com.example.customerservice.dto.CustomerResponse;
import com.example.customerservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerServiceApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestDataFactory testDataFactory;

    @Test
    @DisplayName("test integration create and find customer")
    void createAndFindCustomer() throws Exception {

        var customer = testDataFactory.getCustomer();
        customer.setContacts(null);
        var customerResponse = testDataFactory.getCustomerResponse();
        customerResponse.setContacts(null);

        customerRepository.save(customer);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/customer/{id}", 1L)
                                                      .contentType("application/json"))
                                     .andExpect(status().isOk())
                                     .andReturn();

        var actualCustomerResponse = mvcResult.getResponse().getContentAsString();
        var actualCustomer = objectMapper.readValue(actualCustomerResponse, CustomerResponse.class);

        assertThat(actualCustomer.getFirstName()).isEqualTo(customerResponse.getFirstName());

    }

}
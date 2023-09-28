package com.example.customerservice.service;

import com.example.customerservice.TestDataFactory;
import com.example.customerservice.dto.CustomerResponse;
import com.example.customerservice.entity.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Needed to use @Mock and @InjectMocks
class CustomerServiceTest {

    private TestDataFactory testDataFactory = new TestDataFactory();

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @DisplayName("test customer service delete")
    @Test
    void givenEmployeeId_whenDeleteEmployee_thenNothing(){
        // given - precondition or setup
        int customerId = 1;
        willDoNothing().given(customerRepository).deleteById(customerId);
        // when -  action or the behaviour that we are going test
        customerService.delete(customerId);
        // then - verify the output
        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @DisplayName("test customer service update")
    @Test
    void givenCustomerObject_whenUpdateCustomer_thenReturnUpdatedCustomer(){
        // given - precondition or setup
        var mockCustomer = testDataFactory.getCustomer();
        mockCustomer.setEmail("123@123.com");
        mockCustomer.setFirstName("Smith");
        var customerRequest = testDataFactory.getCustomerRequest();
        customerRequest.setEmail("123@123.com");
        customerRequest.setFirstName("Smith");

        given(customerRepository.findById(1)).willReturn(Optional.of(mockCustomer));
        given(customerRepository.save(mockCustomer)).willReturn(mockCustomer);

        // when -  action or the behaviour that we are going test
        CustomerResponse updatedCustomer = customerService.update(mockCustomer.getId(), customerRequest);

        // then - verify the output
        assertThat(updatedCustomer.getEmail()).isEqualTo("123@123.com");
        assertThat(updatedCustomer.getFirstName()).isEqualTo("Smith");
    }

    @DisplayName("test customer service get all")
    @Test
    void givenCustomerList_whenGetAllCustomer_thenReturnCustomerList() {
        // given - precondition or setup
        List<Customer> customers = testDataFactory.getCustomers();
        given(customerRepository.findAll(PageRequest.of(0,2)))
                .willReturn(new PageImpl<>(customers));
        // when -  action or the behaviour that we are going test
        Page<CustomerResponse> customersResponse = customerService.getAll(0, 2);

        // then - verify the output
        assertThat(customersResponse).isNotNull();
        assertThat(customersResponse.getTotalElements()).isEqualTo(2);
    }

    @DisplayName("test customer service get all (negative scenario)")
    @Test
    void givenEmptyCustomerList_whenGetAllCustomer_thenReturnEmptyCustomerList() {
        given(customerRepository.findAll(PageRequest.of(0,2)))
                .willReturn(new PageImpl<>(Collections.emptyList()));
        // when -  action or the behaviour that we are going test
        Page<CustomerResponse> customersResponse = customerService.getAll(0,2);

        // then - verify the output
        assertThat(customersResponse).isEmpty();
    }

    @DisplayName("test customer service get by id")
    @Test
    void getById() {
        var mockCustomer = testDataFactory.getCustomer();
        // given
        given(customerRepository.findById(1)).willReturn(Optional.of(mockCustomer));
        // when
        CustomerResponse customerResponse = customerService.getById(mockCustomer.getId());
        // then
        assertThat(customerResponse).isNotNull();
    }

    @DisplayName("test customer service get all (with exception)")
    @Test
    void getByIdWithException() {
        // given
        given(customerRepository.findById(1)).willReturn(Optional.empty());
        // when -  action or the behaviour that we are going test
        assertThrows(NoSuchElementException.class, () -> {
            customerService.getById(1);
        });
        // then
        verify(customerRepository, never()).getById(1);
    }
}
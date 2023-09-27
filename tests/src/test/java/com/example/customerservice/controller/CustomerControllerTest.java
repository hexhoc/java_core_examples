package com.example.customerservice.controller;

import java.util.Optional;

import com.example.customerservice.TestDataFactory;
import com.example.customerservice.controller.exception.ApiError;
import com.example.customerservice.dto.CustomerResponse;
import com.example.customerservice.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private TestDataFactory testDataFactory = new TestDataFactory();

    @MockBean
    private CustomerService customerService;

    @Test
    @DisplayName("test customer controller get by id")
    void getById() throws Exception {
        var customerResponse = testDataFactory.getCustomerResponse();
        given(customerService.getById(1)).willReturn(customerResponse);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/customer/{id}", 1L)
                                                      .contentType("application/json"))
                                     .andExpect(status().isOk())
                                     .andReturn();

        var actualCustomerResponse = mvcResult.getResponse().getContentAsString();

        assertThat(actualCustomerResponse).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(customerResponse));
    }

    @Test
    @DisplayName("test customer controller delete")
    void deleteSuccess() throws Exception {
        mockMvc.perform(delete("/api/v1/customer/{id}", 1L)
                                .contentType("application/json"))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("test customer controller update (success)")
    void updateSuccess() throws Exception {
        var customerRequest = testDataFactory.getCustomerRequest();

        mockMvc.perform(put("/api/v1/customer/{id}", 1L)
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(customerRequest)))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("test customer controller update (bad request)")
    void updateBadRequest() throws Exception {
        var customerRequest = testDataFactory.getCustomerRequest();

        customerRequest.setFirstName(null);
        mockMvc.perform(put("/api/v1/customer/{id}", 1L)
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(customerRequest)))
               .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("test customer controller exception handler")
    void whenNullValue_thenReturns400AndErrorResult() throws Exception {
        var customerRequest = testDataFactory.getCustomerRequest();
        customerRequest.setFirstName(null);

        MvcResult mvcResult = mockMvc.perform(put("/api/v1/customer/{id}", 1L)
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(customerRequest)))
                                     .andExpect(status().isBadRequest())
                                     .andReturn();


        ApiError expectedApiError = new ApiError("Method Argument Not Valid", "");
        ApiError actualApiError = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ApiError.class);

        assertThat(expectedApiError.getMessage())
                .isEqualToIgnoringWhitespace(actualApiError.getMessage());
    }

}
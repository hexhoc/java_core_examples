package com.example.customerservice;

import java.util.List;

import com.example.customerservice.dto.CustomerRequest;
import com.example.customerservice.dto.CustomerResponse;
import com.example.customerservice.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Генератор тестовых данных.
 */
@Component
public class TestDataFactory {

    private ObjectMapper objectMapper = new ObjectMapper();

    public static final String CUSTOMER = "/data/customer.json";
    public static final String CUSTOMERS = "/data/customers.json";
    public static final String CUSTOMER_REQUEST = "/data/customer_request.json";
    public static final String CUSTOMER_RESPONSE = "/data/customer_response.json";

    /**
     * Генерирует тестового покупателя.
     *
     * @return Покупатель.
     */
    public Customer getCustomer() {
        return getResource(CUSTOMER, new TypeReference<>() { });
    }

    /**
     * Генерирует тестового покупателя.
     *
     * @return Список покупателей.
     */
    public List<Customer> getCustomers() {
        return getResource(CUSTOMERS, new TypeReference<>() { });
    }

    /**
     * Генерирует тестовый запрос покупателя.
     *
     * @return запрос покупателя.
     */
    public CustomerRequest getCustomerRequest() {
        return getResource(CUSTOMER_REQUEST, new TypeReference<>() { });
    }

    /**
     * Генерирует тестовый ответ покупателя.
     *
     * @return Ответ покупателя.
     */
    public CustomerResponse getCustomerResponse() {
        return getResource(CUSTOMER_RESPONSE, new TypeReference<>() { });
    }

    /**
     * Загружает ресурс.
     *
     * @return Ресурс.
     */
    @SneakyThrows
    private <T> T getResource(final String path, final TypeReference<T> typeReference) {
        return objectMapper.readValue(getClass().getResource(path), typeReference);
    }
}
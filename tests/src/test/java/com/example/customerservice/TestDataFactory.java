package com.example.customerservice;

import com.example.customerservice.dto.CustomerRequest;
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

    @Autowired
    private ObjectMapper objectMapper;

    public static final String CUSTOMER = "/data/customer.json";
    public static final String CUSTOMER_REQUEST = "/data/customer_request.json";
    public static final String CUSTOMER_RESPONSE = "/data/customer_response.json";

    /**
     * Генерирует тестового покупателя.
     *
     * @return Пользователь.
     */
    public CustomerRequest getCustomer() {
        return getResource(CUSTOMER, new TypeReference<>() { });
    }

    /**
     * Генерирует тестовый запрос покупателя.
     *
     * @return запрос пользователя.
     */
    public CustomerRequest getCustomerRequest() {
        return getResource(CUSTOMER_REQUEST, new TypeReference<>() { });
    }

    /**
     * Генерирует тестовый ответ покупателя.
     *
     * @return Ответ пользователя.
     */
    public CustomerRequest getCustomerResponse() {
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
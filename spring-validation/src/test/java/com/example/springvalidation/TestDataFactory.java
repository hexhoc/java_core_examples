package com.example.springvalidation;


import com.example.springvalidation.dto.PersonDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

/**
 * Генератор тестовых данных.
 */
@Component
public class TestDataFactory {

    private ObjectMapper objectMapper = new ObjectMapper();

    public static final String PERSON = "/data/person.json";

    public PersonDto getPerson() {
        return getResource(PERSON, new TypeReference<>() { });
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
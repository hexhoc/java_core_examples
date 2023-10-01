package com.example.springvalidation.controller;

import com.example.springvalidation.TestDataFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private TestDataFactory testDataFactory = new TestDataFactory();

    @Test
    @DisplayName("Тест. Полная валидация пройдена")
    void validReturnOk() throws Exception {
        var person = testDataFactory.getPerson();
        mockMvc.perform(post("/api/v1/person/valid")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(person)))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Тест. Полная валидация НЕ пройдена")
    void validReturnBad() throws Exception {
        var person = testDataFactory.getPerson();
        person.setEmail("not valid email");

        mockMvc.perform(post("/api/v1/person/valid")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(person)))
               .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Тест. Проверка при создании пройдена")
    void createReturnOk() throws Exception {
        var person = testDataFactory.getPerson();
        person.setId(null);

        mockMvc.perform(post("/api/v1/person/create")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(person)))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Тест. Проверка при создании НЕ пройдена")
    void createReturnBad() throws Exception {
        var person = testDataFactory.getPerson();
        person.setId(1L);

        mockMvc.perform(post("/api/v1/person/create")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(person)))
               .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Тест. Проверка при обновлении пройдена")
    void updateReturnOk() throws Exception {
        var person = testDataFactory.getPerson();
        person.setId(1L);

        mockMvc.perform(put("/api/v1/person/update")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(person)))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Тест. Проверка при обновлении НЕ пройдена")
    void updateReturnBad() throws Exception {
        var person = testDataFactory.getPerson();
        person.setId(null);

        mockMvc.perform(put("/api/v1/person/update")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(person)))
               .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Тест. Ручная проверка пройдена")
    void manualValidReturnOk() throws Exception {
        var person = testDataFactory.getPerson();
        person.setId(1L);

        mockMvc.perform(post("/api/v1/person/manualvalid")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(person)))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Тест. Ручная проверка НЕ пройдена")
    void manualValidReturnBad() throws Exception {
        var person = testDataFactory.getPerson();
        person.setId(null);

        mockMvc.perform(post("/api/v1/person/manualvalid")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(person)))
               .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Тест. Получить по id, проверка пройдена")
    void getByIdReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/person/{id}", 1)
                                .contentType("application/json"))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Тест. Получить по id, проверка НЕ пройдена")
    void getByIdReturnBad() throws Exception {
        mockMvc.perform(get("/api/v1/person/{id}", -1)
                                .contentType("application/json"))
               .andExpect(status().isBadRequest());
    }


    @Test
    void getByName() {
    }

}
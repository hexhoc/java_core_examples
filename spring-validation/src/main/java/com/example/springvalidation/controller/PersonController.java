package com.example.springvalidation.controller;

import java.util.Set;

import com.example.springvalidation.dto.PersonDto;
import com.example.springvalidation.validation.Marker;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/person")
@Validated // нужна только для срабатывания валидации параметров в методах getById и getByName
public class PersonController {

    /**
     * Полная проверка dto
     * http://localhost:8080/api/v1/person/valid
     */
    @PostMapping("/valid")
    public ResponseEntity<String> valid(@Valid @RequestBody PersonDto personDto) {
        return ResponseEntity.ok("valid");
    }

    /**
     * Полная проверка dto + полей которые помечены группой OnCreate
     * http://localhost:8080/api/v1/person/create
     */
    @PostMapping("/create")
    public ResponseEntity<String> create(@Validated({Marker.OnCreate.class}) @RequestBody PersonDto personDto) {
        return ResponseEntity.ok("valid");
    }

    /**
     * Полная проверка dto + полей которые помечены группой OnUpdate
     * http://localhost:8080/api/v1/person/update
     */
    @PutMapping("/update")
    public ResponseEntity<String> update(@Validated(Marker.OnUpdate.class) @RequestBody PersonDto personDto) {
        return ResponseEntity.ok("valid");
    }

    /**
     * Ручной запуск проверки dto
     * http://localhost:8080/api/v1/person/manualvalid
     */
    @PostMapping("/manualvalid")
    public ResponseEntity<String> manualValid(@Valid @RequestBody PersonDto personDto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<PersonDto>> violations = factory.getValidator().validate(personDto,Marker.OnUpdate.class);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return ResponseEntity.ok("valid");
    }

    /**
     * Проверка входящих параметров
     * http://localhost:8080/api/v1/person/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") @Min(0) int personId) {
        return ResponseEntity.ok("valid");
    }

    /**
     * Проверка входящих параметров
     * http://localhost:8080/api/v1/person?name=test
     */
    @GetMapping
    public ResponseEntity<String> getByName(@RequestParam("name") @NotBlank String name) {
        return ResponseEntity.ok("valid");
    }

}

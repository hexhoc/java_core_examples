package com.example.springjpa.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Критерии фильтрации для пользователей.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Критерии фильтрации для пользователей")
public class AuthorFilterRequest {

    @Parameter(description = "Имя")
    private String firstName;

    @Parameter(description = "Фамилия")
    private String lastName;

}

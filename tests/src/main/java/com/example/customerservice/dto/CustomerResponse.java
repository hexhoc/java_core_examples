package com.example.customerservice.dto;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Customer data transfer object. Used for request and response")
public class CustomerResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Integer id;
    
    @Schema(description = "first name")
    private String firstName;

    @Schema(description = "last name")
    private String lastName;

    @Schema(description = "email")
    private String email;

    @Schema(description = "phone")
    private String phone;

    @Schema(description = "is deleted")
    private Boolean isDeleted = false;

    @Schema(description = "created date")
    private Timestamp createdDate;

    @Schema(description = "modified date")
    private Timestamp modifiedDate;

    @Schema(description = "version")
    private Long version;

    @Schema(description = "contacts")
    private List<CustomerContactDto> contacts;

}

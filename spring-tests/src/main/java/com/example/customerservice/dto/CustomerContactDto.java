package com.example.customerservice.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Customer contact data transfer object. Used for request and response")
public class CustomerContactDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "id")
    private UUID id;

    @Schema(name = "customerId")
    private Integer customerId;

    @Schema(name = "address")
    private String address;

}
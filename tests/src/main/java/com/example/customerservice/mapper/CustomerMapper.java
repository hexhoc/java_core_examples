package com.example.customerservice.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.customerservice.dto.CustomerContactDto;
import com.example.customerservice.dto.CustomerRequest;
import com.example.customerservice.dto.CustomerResponse;
import com.example.customerservice.entity.Customer;
import com.example.customerservice.entity.CustomerContact;

public class CustomerMapper {
    public static CustomerResponse toDto(Customer entity) {
        var dto = new CustomerResponse();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setVersion(entity.getVersion());

        var contactsEntity = entity.getContacts();
        List<CustomerContactDto> contacts = new ArrayList<>();
        for (var contact : contactsEntity) {
            contacts.add(new CustomerContactDto(contact.getId(), contact.getCustomerId(), contact.getAddress()));
        }
        dto.setContacts(contacts);
        return dto;
    }

    public static Customer toEntity(CustomerRequest dto) {
        var entity = new Customer();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());

        var contactsDto = dto.getContacts();
        List<CustomerContact> contacts = new ArrayList<>();
        for (var contact : contactsDto) {
            contacts.add(new CustomerContact(contact.getId(), contact.getCustomerId(), null, contact.getAddress()));
        }
        entity.setContacts(contacts);
        return entity;
    }
}

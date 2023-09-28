package com.example.customerservice.service;

import com.example.customerservice.dto.CustomerRequest;
import com.example.customerservice.dto.CustomerResponse;
import com.example.customerservice.mapper.CustomerMapper;
import com.example.customerservice.entity.Customer;
import com.example.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

    public CustomerResponse save(CustomerRequest dto) {
        Customer newEntity = CustomerMapper.toEntity(dto);
        return CustomerMapper.toDto(customerRepository.save(newEntity));
    }

    public CustomerResponse update(Integer id, CustomerRequest dto) {
        Customer entity = requireOne(id);
        Customer updatedEntity = CustomerMapper.toEntity(dto);
        BeanUtils.copyProperties(updatedEntity, entity ,"id", "version","createdDate","modifiedDate");
        return CustomerMapper.toDto(customerRepository.save(entity));
    }

    public Page<CustomerResponse> getAll(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);

        Page<Customer> entityPage = customerRepository.findAll(pageRequest);
        List<CustomerResponse> dtoList = entityPage.stream()
                .map(CustomerMapper::toDto)
                .toList();

        return new PageImpl<>(dtoList);
    }

    public CustomerResponse getById(Integer id) {
        return CustomerMapper.toDto(requireOne(id));
    }

    private Customer requireOne(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}

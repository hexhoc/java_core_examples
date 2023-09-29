package com.example.springjpa.service;

import com.example.springjpa.dto.AuthorFilterRequest;
import com.example.springjpa.entity.manytomany.Author;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.predicate.AuthorSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Page<Author> get(AuthorFilterRequest request, Pageable pageable) {
        return authorRepository.findAll(new AuthorSpecification(request), pageable);
    }


}

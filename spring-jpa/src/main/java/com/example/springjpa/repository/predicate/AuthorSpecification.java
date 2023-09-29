package com.example.springjpa.repository.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.springjpa.dto.AuthorFilterRequest;
import com.example.springjpa.entity.manytomany.Author;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * Строитель предикатов для фильтрации пользователей.
 */
@RequiredArgsConstructor
public class AuthorSpecification implements Specification<Author> {
    private final AuthorFilterRequest userFilter;

    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (Objects.nonNull(userFilter.getFirstName())) {
            predicates.add(builder.equal(root.get("firstName"), String.valueOf(userFilter.getFirstName())));
        }
        if (Objects.nonNull(userFilter.getLastName())) {
            predicates.add(builder.equal(root.get("lastName"), String.valueOf(userFilter.getLastName())));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
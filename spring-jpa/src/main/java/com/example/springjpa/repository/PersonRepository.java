package com.example.springjpa.repository;

import com.example.springjpa.dto.PersonDto;
import com.example.springjpa.entity.onetoone.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query(value = """
        SELECT new com.example.springjpa.dto.PersonDto(p.id, p.personName, p.fingerprint.md5Hash)
        FROM Person p
        WHERE p.id = :id
        """)
    PersonDto findPersonDtoById(Integer id);
    
}
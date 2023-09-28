package com.example.springjpa.entity.onetoone;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "persons")
@Getter
@Setter
public class Person {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "person_name")
    private String personName;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private Fingerprint fingerprint;
}

package com.example.springjpa.entity.manytomany;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(name = "adjustment_transportation_unit",
               joinColumns = @JoinColumn(name = "adjustment_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "transportation_unit_id", referencedColumnName = "id")
    )
    private Set<Author> authors;

}

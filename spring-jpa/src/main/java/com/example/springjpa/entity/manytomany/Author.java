package com.example.springjpa.entity.manytomany;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "authors")
@Getter
@Setter
public class Author {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "author_name")
    private String authorName;

    @ManyToMany(mappedBy = "authors")
    Set<Book> books;
}

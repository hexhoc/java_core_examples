
# Hibernate

## Description

Java Persistence API. Basics of using Hibernate. Entities.
Cascading operations. @OneToOne, @OneToMany, @ManyToOne,
@ManyToMany.

## Installation
1. Create and configure /src/main/resources/hibernate.properties
2. Create and configure class that build SessionFactory. For example: /src/main/java/config/HibernateUtil.java

## EXAMPLES 
To work with annotations for class fields, you can use an example from the class "AnnotatedClass"


## Exercises

### Exercise 1. CRUD
1. Create table "catalogs" with columns - "id" and "title"
2. Create entity for catalog
3. Create new record in catalog using hibernate
4. Read you new record
5. Update title and save
6. Delete you record

### Exercise 2. Relation
1. Create table "authors". Columns - id, name
2. Create table "books". Columns id, title, author_id
3. Create table "readers". Columns id, name
4. Create table "books_readers". Column book_id, reader_id
5. Create entity for tables and describe relation between them.



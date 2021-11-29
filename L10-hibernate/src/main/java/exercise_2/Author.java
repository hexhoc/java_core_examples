package exercise_2;

import javax.persistence.*;
import java.util.List;

@Entity(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //authors table do not have reference to the book (book_id), but book has a reference to the author
    //Because of that we use mappedBy.
    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author() { }

    public Author(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

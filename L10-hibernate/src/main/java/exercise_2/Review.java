package exercise_2;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "books_readers")
public class Review {


    @Embeddable
    static class Id implements Serializable {
        @Column(name = "books_id")
        Long bookId;
        @Column(name = "readers_id")
        Long readerId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Id id = (Id) o;

            if (!bookId.equals(id.bookId)) return false;
            return readerId.equals(id.readerId);
        }

        @Override
        public int hashCode() {
            int result = bookId.hashCode();
            result = 31 * result + readerId.hashCode();
            return result;
        }
    }

    @EmbeddedId
    private Id id;

//    @ManyToOne
//    @JoinColumn(name = "books_id")
//    private Book book;
//
//    @ManyToOne
//    @JoinColumn(name = "readers_id")
//    private Reader reader;
}

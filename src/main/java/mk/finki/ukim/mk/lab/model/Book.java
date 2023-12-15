package mk.finki.ukim.mk.lab.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String isbn;
    private String title;
    private String genre;
    private int year;

    @ManyToMany
    @Nullable
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "book_id", nullable = true)
    )
    private List<Author> authors;
    @ManyToOne
    private BookStore bookStore;
//    @OneToMany
//    @JoinColumn(name = "review_id")
//    private List<Review> reviews;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title);
        sb.append(", ");
        sb.append("Genre: ").append(genre);
        sb.append(", ");
        sb.append("Year: ").append(year);
        sb.append(", ");
        sb.append("Book Store Name: ").append(bookStore.getName());
        return sb.toString();
    }

    public Book(String isbn, String title, String genre, int year, Author author, BookStore bookStore) {
        this.id = (long) (Math.random() * 1000);
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = new ArrayList<>();
        authors.add(author);
        this.bookStore = bookStore;
//        this.reviews = new ArrayList<>();
    }

    public Book(String isbn, String title, String genre, int year, List<Author> authors, BookStore bookStore) {
        this.id = 0;
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        this.bookStore = bookStore;
//        this.reviews = new ArrayList<>();
    }

    public Book() {
//        this.reviews = new ArrayList<>();
        this.authors = new ArrayList<>();
    }
}

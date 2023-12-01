package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Book {
    private long id;
    private String isbn;
    private String title;
    private String genre;
    private int year;
    private List<Author> authors;
    private BookStore bookStore;

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
    }

    public Book(String isbn, String title, String genre, int year, List<Author> authors, BookStore bookStore) {
        this.id = (long) (Math.random() * 1000);
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        this.bookStore = bookStore;
    }

    public Book() {
        this.authors = new ArrayList<>();
    }
}

package mk.finki.ukim.mk.lab.util;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public List<Author> authors;
    public List<Book> books;
    public List<BookStore> bookStores;

    @PostConstruct
    private void init() {
        authors = new ArrayList<>();

        authors.add(new Author(1L, "John", "Smith", "Biography 1", LocalDate.now(), new ArrayList<>()));
        authors.add(new Author(2L, "John", "Doe", "Biography 2", LocalDate.now(), new ArrayList<>()));
        authors.add(new Author(3L, "John", "Doe", "Biography 2", LocalDate.now(), new ArrayList<>()));
        authors.add(new Author(4L, "JRR", "Tolkien", "Biography 3", LocalDate.now(), new ArrayList<>()));
        authors.add(new Author(5L, "Gene", "Wolfe", "Biography 4", LocalDate.now(), new ArrayList<>()));
        authors.add(new Author(6L, "Arthur", "C. Clarke", "Biography 5", LocalDate.now(), new ArrayList<>()));
        authors.add(new Author(7L, "JRR", "Tolkien", "Biography 3", LocalDate.now(), new ArrayList<>()));
        authors.add(new Author(8L, "Gene", "Wolfe", "Biography 4", LocalDate.now(), new ArrayList<>()));
        authors.add(new Author(9L, "Arthur", "C. Clarke", "Biography 6", LocalDate.now(), new ArrayList<>()));

        bookStores = new ArrayList<>();
        bookStores.add(new BookStore(1L, "Knizhara1", "Skopje", "Adresa 1"));
        bookStores.add(new BookStore(2L, "Knizhara2", "Veles", "Adresa 2"));
        bookStores.add(new BookStore(3L, "Knizhara3", "Skopje", "Adresa 3"));
        bookStores.add(new BookStore(4L, "Knizhara4", "Bitola", "Adresa 4"));

        books = new ArrayList<>();
        books.add(new Book("ISBN 978-3-16-148410-0", "Title", "Genre", 2000, authors, bookStores.get(1)));
        books.add(new Book("ISBN " + generateISBN(), "Childhood's End", "Scifi", 2000, authors.get(4), bookStores.get(1)));
        books.add(new Book("ISBN " + generateISBN(), "Fifth Head of Cerberus", "Genre", 2000, authors.get(3), bookStores.get(1)));
        books.add(new Book("ISBN " + generateISBN(), "Lord of the Rings", "Genre", 2000, authors.get(2), bookStores.get(3)));
        books.add(new Book("ISBN " + generateISBN(), "Title", "Genre", 2000, authors, bookStores.get(2)));
    }

    private String generateISBN(){
        StringBuilder sb = new StringBuilder();
        sb.append((int)(Math.random()*1000));
        sb.append('-');
        sb.append((int)(Math.random()*10));
        sb.append('-');
        sb.append((int)(Math.random()*100));
        sb.append('-');
        sb.append((int)(Math.random()*1000000));
        sb.append('-');
        sb.append((int)(Math.random()*10));
        return sb.toString();
    }
}

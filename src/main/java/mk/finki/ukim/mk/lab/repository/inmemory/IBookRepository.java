package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository {
    public List<Book> findAll();
    public Optional<Book> findByIsbn(String isbn);
    Author addAuthorToBook(Author author, Book book);

    Book findById(long id);

    void saveBook(Book book);

    void deleteById(long id);

    void editBook(long id, Book book);
}

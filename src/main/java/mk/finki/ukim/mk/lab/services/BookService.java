package mk.finki.ukim.mk.lab.services;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
    Book getBookById(long id);

    void saveBook(Book book);


    void deleteById(long id);

    void editBook(long id, Book book);

    void editBook(Book book);

//    void addReviewToBook(long id, Review review);
}

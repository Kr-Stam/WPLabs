package mk.finki.ukim.mk.lab.services.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepository;
import mk.finki.ukim.mk.lab.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Author author = authorRepository.findById(authorId).orElse(null);
        Book book = bookRepository.getReferenceByIsbn(isbn).orElse(null);
        if(book != null && author != null) {
            book.getAuthors().add(author);
            bookRepository.save(book);
        }
        return author;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElse(null);
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void editBook(long id, Book book) {
        Book oldBook = bookRepository.getReferenceById(id);
        oldBook.setTitle(book.getTitle());
        oldBook.setBookStore(book.getBookStore());
        oldBook.setAuthors(book.getAuthors());
        oldBook.setIsbn(book.getIsbn());
        oldBook.setGenre(book.getGenre());
        oldBook.setYear(book.getYear());
        bookRepository.save(oldBook);
    }

    @Override
    public void editBook(Book book) {
        Book oldBook = bookRepository.getReferenceById(book.getId());
        oldBook.setTitle(book.getTitle());
        oldBook.setBookStore(book.getBookStore());
        oldBook.setAuthors(book.getAuthors());
        oldBook.setIsbn(book.getIsbn());
        oldBook.setGenre(book.getGenre());
        oldBook.setYear(book.getYear());
        bookRepository.save(oldBook);
    }

//    @Override
//    public void addReviewToBook(long id, Review review) {
//        Book book = bookRepository.getReferenceById(id);
//        book.getReviews().add(review);
//        bookRepository.save(book);
//    }
}

package mk.finki.ukim.mk.lab.services.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.repository.IAuthorRepository;
import mk.finki.ukim.mk.lab.repository.IBookRepository;
import mk.finki.ukim.mk.lab.repository.InMemoryAuthorRepository;
import mk.finki.ukim.mk.lab.repository.InMemoryBookRepository;
import mk.finki.ukim.mk.lab.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private IAuthorRepository authorRepository;
    private IBookRepository bookRepository;

    public BookServiceImpl(InMemoryAuthorRepository authorRepository, InMemoryBookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);
        if(authorOptional.isPresent() && bookOptional.isPresent()) {
            return bookRepository.addAuthorToBook(authorOptional.get(), bookOptional.get());
        }
        return null;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);
        return bookOptional.orElse(null);
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.saveBook(book);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void editBook(long id, Book book) {
        bookRepository.editBook(id, book);
    }
}

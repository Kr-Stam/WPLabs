package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.util.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository implements IBookRepository {

    private DataHolder dataHolder;

    public InMemoryBookRepository(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    @Override
    public List<Book> findAll() {
        return dataHolder.books;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return dataHolder.books.stream().filter(x -> x.getIsbn().equals(isbn)).findFirst();
    }

    @Override
    public Author addAuthorToBook(Author author, Book book) {
        dataHolder.books.stream().filter(x -> x.getIsbn().equals(book.getIsbn())).forEach(x -> x.getAuthors().add(author));
        return author;
    }

    @Override
    public Book findById(long id) {
        return dataHolder.books.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void saveBook(Book book) {
        dataHolder.books.add(book);
    }

    @Override
    public void deleteById(long id) {
        dataHolder.books = dataHolder.books.stream().filter(x -> x.getId() != id).collect(Collectors.toList());
    }

    @Override
    public void editBook(long id, Book book) {
        Book book1 = findById(id);
        book1.setTitle(book.getTitle());
        book1.setGenre(book.getGenre());
        book1.setYear(book.getYear());
        book1.setBookStore(book.getBookStore());
        book1.setIsbn(book.getIsbn());
    }
}

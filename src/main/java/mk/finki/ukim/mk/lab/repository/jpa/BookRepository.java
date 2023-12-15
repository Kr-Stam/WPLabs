package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
//    List<Book> getAll();
    List<Book> findAllByTitleLike(String title);
    List<Book> findAllByBookStore_Id(long id);
    List<Book> findAllByAuthorsContaining(Author author);
    Optional<Book> findById(long id);
    Optional<Book> findByIsbn(String isbn);
    void deleteById(long id);
    Optional<Book> getReferenceByIsbn(String isbn);
}

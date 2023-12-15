package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {
//    List<BookStore> getAll();
    List<BookStore> findAllByNameLike(String name);
    BookStore findById(long id);
    void deleteById(long id);
}

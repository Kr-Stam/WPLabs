package mk.finki.ukim.mk.lab.repository.jpa;

import jdk.jfr.Category;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
//    List<Author> findAllByNameLike(String name);
//    List<Author> getAll();
//    void deleteByName(String name);
    void deleteById(long id);
    Author findById(long id);
}

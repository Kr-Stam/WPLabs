package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAuthorRepository {

    public List<Author> findAll();
    public Optional<Author> findById(long id);
    public Optional<Author> findByName(String name);
}

package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.util.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository implements IAuthorRepository{

    private DataHolder dataHolder;

    public InMemoryAuthorRepository(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    @Override
    public List<Author> findAll() {
        return dataHolder.authors;
    }

    @Override
    public Optional<Author> findById(long id) {
        return dataHolder.authors.stream().filter(x -> x.getId() == id).findFirst();
    }

    @Override
    public Optional<Author> findByName(String name) {
        return dataHolder.authors.stream().filter(x -> x.getName().equals(name)).findFirst();
    }
}

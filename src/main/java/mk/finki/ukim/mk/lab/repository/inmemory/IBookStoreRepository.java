package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.model.BookStore;

import java.util.List;

public interface IBookStoreRepository {
    public List<BookStore> findAll();

    BookStore findById(long id);
}

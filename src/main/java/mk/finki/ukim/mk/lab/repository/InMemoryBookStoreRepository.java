package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.util.DataHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryBookStoreRepository implements IBookStoreRepository{

    private DataHolder dataHolder;

    public InMemoryBookStoreRepository(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }


    @Override
    public List<BookStore> findAll() {
        return dataHolder.bookStores;
    }

    @Override
    public BookStore findById(long id) {
        return dataHolder.bookStores.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
}

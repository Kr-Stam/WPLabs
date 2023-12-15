package mk.finki.ukim.mk.lab.services;

import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookStoreService {

    public List<BookStore> findAll();

    BookStore findById(long id);

    void saveBookStore(BookStore bookStore);
}

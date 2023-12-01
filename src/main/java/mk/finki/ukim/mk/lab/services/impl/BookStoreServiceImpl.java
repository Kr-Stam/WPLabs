package mk.finki.ukim.mk.lab.services.impl;

import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.IBookStoreRepository;
import mk.finki.ukim.mk.lab.repository.InMemoryBookStoreRepository;
import mk.finki.ukim.mk.lab.services.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private IBookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(InMemoryBookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public BookStore findById(long id) {
        return bookStoreRepository.findById(id);
    }
}

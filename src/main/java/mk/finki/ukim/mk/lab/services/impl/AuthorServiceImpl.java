package mk.finki.ukim.mk.lab.services.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.repository.IAuthorRepository;
import mk.finki.ukim.mk.lab.repository.InMemoryAuthorRepository;
import mk.finki.ukim.mk.lab.repository.InMemoryBookRepository;
import mk.finki.ukim.mk.lab.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private IAuthorRepository authorRepository;

    public AuthorServiceImpl(InMemoryAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
}

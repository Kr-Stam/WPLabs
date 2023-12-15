package mk.finki.ukim.mk.lab.services;

import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    List<Author> listAuthors();
    Author findById(Long id);
    void deleteById(long id);

    void saveAuthor(Author author);
}

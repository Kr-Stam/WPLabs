package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.services.AuthorService;
import mk.finki.ukim.mk.lab.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorController {

    private AuthorService authorService;
    private BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/books/add-author/{id}")
    public String addAuthorToBook(@PathVariable("id") long id, Model model){
        model.addAttribute("authors", authorService.listAuthors());
        model.addAttribute("book", bookService.getBookById(id));
//        System.out.println(bookService.listBooks());
        return "author-list";
    }

    @PostMapping("/books/add-author/{id}")
    public String addAuthorToBook(
            @PathVariable("id") long id,
            Model model,
            @RequestParam("authorId") long authorId){
        Book book = bookService.getBookById(id);
        Author author = authorService.findById(authorId);
        book.getAuthors().add(author);
        return "redirect:/books/" + id;
    }
}

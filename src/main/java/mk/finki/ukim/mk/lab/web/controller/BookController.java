package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.services.BookService;
import mk.finki.ukim.mk.lab.services.BookStoreService;
import mk.finki.ukim.mk.lab.services.impl.BookServiceImpl;
import mk.finki.ukim.mk.lab.services.impl.BookStoreServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private BookService bookService;
    private BookStoreService bookStoreService;

    public BookController(BookServiceImpl bookService, BookStoreServiceImpl bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping("/books")
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Book> books = this.bookService.listBooks();
        model.addAttribute("books", books);

        return "listbooks";
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable("id") long id, Model model){
        Book book = bookService.getBookById(id);

        if(book != null){
            model.addAttribute("book", book);
        }

        return "bookdetails";
    }

    @GetMapping("books/add")
    public String addBooks(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        List<BookStore> bookStores = bookStoreService.findAll();
        System.out.println(bookStores);
        model.addAttribute("bookStores", bookStores);
        model.addAttribute("pageTitle", "Add a Book");
        return "add-book";
    }
    @PostMapping("books/add")
    public String addBook(
            @RequestParam("bookTitle") String bookTitle,
            @RequestParam("bookGenre") String bookGenre,
            @RequestParam("bookYear") int bookYear,
            @RequestParam("bookIsbn") String bookIsbn,
            @RequestParam("bookStore") long bookStoreId
    ){
        BookStore bookStore = bookStoreService.findById(bookStoreId);
        Book book = new Book(bookIsbn, bookTitle, bookGenre, bookYear, (Author) null, bookStore);
        bookService.saveBook(book);
        return "redirect:/books";
    }
    @GetMapping("books/edit/{id}")
    public String editBook(@PathVariable("id") long id, Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        List<BookStore> bookStores = bookStoreService.findAll();
        System.out.println(bookStores);
        model.addAttribute("bookStores", bookStores);
        model.addAttribute("pageTitle", "Edit a Book");
        return "add-book";
    }
    @PostMapping("books/edit/{id}")
    public String editBook(
            @RequestParam("bookTitle") String bookTitle,
            @RequestParam("bookGenre") String bookGenre,
            @RequestParam("bookYear") int bookYear,
            @RequestParam("bookIsbn") String bookIsbn,
            @RequestParam("bookStore") long bookStoreId,
            @PathVariable("id") long id
    ){
        BookStore bookStore = bookStoreService.findById(bookStoreId);
        Book book = new Book(bookIsbn, bookTitle, bookGenre, bookYear, (Author) null, bookStore);
        bookService.editBook(id, book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") long id){
        bookService.deleteById(id);

        return "redirect:/books";
    }
}

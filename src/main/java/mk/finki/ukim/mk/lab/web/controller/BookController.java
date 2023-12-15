package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.services.AuthorService;
import mk.finki.ukim.mk.lab.services.BookService;
import mk.finki.ukim.mk.lab.services.BookStoreService;
import mk.finki.ukim.mk.lab.services.impl.AuthorServiceImpl;
import mk.finki.ukim.mk.lab.services.impl.BookServiceImpl;
import mk.finki.ukim.mk.lab.services.impl.BookStoreServiceImpl;
import mk.finki.ukim.mk.lab.services.impl.ReviewServiceImpl;
import mk.finki.ukim.mk.lab.util.DataHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BookController {

    private BookService bookService;
    private BookStoreService bookStoreService;
    private AuthorService authorService;
    private ReviewServiceImpl reviewService;
    private DataHolder dataHolder;

    public BookController(
            BookServiceImpl bookService,
            BookStoreServiceImpl bookStoreService,
            AuthorServiceImpl authorService,
            ReviewServiceImpl reviewService,
            DataHolder dataHolder) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
        this.authorService = authorService;
        this.reviewService = reviewService;

        this.dataHolder = dataHolder;
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

        model.addAttribute("showCommentForm", false);
        model.addAttribute("tmpReview", new Review());

        model.addAttribute("reviews", reviewService.getReviewsByBook(id));
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
    @GetMapping("books/init")
    public String initBooks(Model model){
        dataHolder.bookStores.forEach(bookStore -> bookStoreService.saveBookStore(bookStore));
        dataHolder.authors.forEach(author -> authorService.saveAuthor(author));
        dataHolder.books.forEach(book -> bookService.saveBook(book));
        System.out.println("Stiga");

        return "redirect:/books";
    }

    @GetMapping("")
    public String redirect(Model model){
        return "redirect:/books";
    }

    @PostMapping("books/{id}/add-review")
    public String addReview(
            @PathVariable long id,
            @ModelAttribute Review review){

        review.setId(0L);
        review.setTimestamp(LocalDateTime.now());

        review.setBook(bookService.getBookById(id));
        reviewService.saveReview(review);


        return "redirect:/books/" + id;
    }
}

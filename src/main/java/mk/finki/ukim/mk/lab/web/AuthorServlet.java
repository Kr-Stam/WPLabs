package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.services.AuthorService;
import mk.finki.ukim.mk.lab.services.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@WebServlet(urlPatterns = {"/author"})
public class AuthorServlet extends HttpServlet {

    private AuthorService authorService;
    private BookService bookService;
    private SpringTemplateEngine springTemplateEngine;

    public AuthorServlet(AuthorService authorService, BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.authorService = authorService;
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbn = req.getParameter("bookIsbn");
        String authorId = req.getParameter("authorId");

        bookService.addAuthorToBook(Long.valueOf(authorId), isbn);

        resp.sendRedirect("/bookDetails?bookIsbn=" + isbn.replace(' ', '+'));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbn = req.getParameter("bookIsbn");

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("authors", authorService.listAuthors());
        context.setVariable("bookIsbn", isbn);

        springTemplateEngine.process(
                "authorList.html",
                context,
                resp.getWriter()
        );
    }
}


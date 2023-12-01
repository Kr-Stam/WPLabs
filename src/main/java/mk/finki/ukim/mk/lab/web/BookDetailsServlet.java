package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.services.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@WebServlet(urlPatterns = {"/bookDetails"})
public class BookDetailsServlet extends HttpServlet {

    private BookService bookService;
    private SpringTemplateEngine springTemplateEngine;

    public BookDetailsServlet(BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);


        String isbn = req.getParameter("bookIsbn");
        System.out.println(isbn);
//        String authorId = req.getParameter("authorId");
//
//        bookService.addAuthorToBook(Long.valueOf(authorId), isbn);
        System.out.println(bookService.findBookByIsbn(isbn));
        context.setVariable("book", bookService.findBookByIsbn(isbn));

        springTemplateEngine.process(
                "bookdetails.html",
                context,
                resp.getWriter()
        );
    }
}

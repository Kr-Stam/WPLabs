package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.services.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/listBooks"})
public class BookListServlet extends HttpServlet {

    private BookService bookService;
    private SpringTemplateEngine springTemplateEngine;

    public BookListServlet(BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        String searchStr = req.getParameter("searchStr");
        List<Book> books = bookService.listBooks();

        String tmp = "";
        if(searchStr != null){
            books = books.stream().filter(x -> x.getTitle().toLowerCase().contains(searchStr.toLowerCase())).toList();
            tmp = searchStr;
        }
        WebContext context = new WebContext(webExchange);
        context.setVariable("books", books);
        context.setVariable("searchStr", tmp);

        springTemplateEngine.process(
                "listbooks.html",
                context,
                resp.getWriter()
        );


}
}

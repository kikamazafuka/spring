package de.semart.sprigcourse.controllers;

import de.semart.sprigcourse.dao.BookDAO;
import de.semart.sprigcourse.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        System.out.println("book controller index");
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String createBook(@ModelAttribute("book") Book book){

        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") Book book){
        bookDAO.save(book);
        return "redirect:/books";
    }
}

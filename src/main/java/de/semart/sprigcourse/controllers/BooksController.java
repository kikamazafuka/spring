package de.semart.sprigcourse.controllers;

import de.semart.sprigcourse.dao.BookDAO;
import de.semart.sprigcourse.dao.PersonDAO;
import de.semart.sprigcourse.models.Book;
import de.semart.sprigcourse.models.Person;
import de.semart.sprigcourse.util.BookValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final BookValidator bookValidator;
    private  final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, BookValidator bookValidator, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        System.out.println("book controller index");
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute ("person") Person person){
        Optional<Person> bookOwner = personDAO.getBookOwner(id);
        if (bookOwner.isPresent()){
            model.addAttribute("bookOwner", bookOwner.get());
        } else {
            model.addAttribute("people", personDAO.index());
        }
        model.addAttribute("book", bookDAO.show(id));

        return "books/show";
    }

    @GetMapping("/new")
    public String createBook(@ModelAttribute("book") Book book){

        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()){
            return "books/new";
        }

        bookDAO.save(book);
        System.out.println("Book controller create");
        return "redirect:/books";
    }



    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id){
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    // ****  Set person to book ****** ///
    @PatchMapping("/add")
    public String setPerson(@ModelAttribute("person") Person person){
       // bookDAO.setPersonId(1, person);
        //TODO

        System.out.println("add person to book");
        return "redirect:/books";
    }
}

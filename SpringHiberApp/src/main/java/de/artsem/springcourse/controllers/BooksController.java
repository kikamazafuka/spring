package de.artsem.springcourse.controllers;

import de.artsem.springcourse.models.Book;
import de.artsem.springcourse.models.Person;
import de.artsem.springcourse.services.BooksService;
import de.artsem.springcourse.services.PeopleService;
import de.artsem.springcourse.util.BookValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Optional;


@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookValidator bookValidator;

    public BooksController(BooksService booksService, PeopleService peopleService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable ("id") int id, Model model){
//        Optional<Book> bookOwner = booksService.findByOwner(id);
//        if (bookOwner.isPresent()){
//            model.addAttribute("bookOwner", bookOwner.get());
//        } else {
//            model.addAttribute("people", personDAO.index());
//        }
        model.addAttribute("book", booksService.findOne(id));
        return "books/show";
    }

//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, Model model,
//                       @ModelAttribute ("person") Person person){
//        Optional<Person> bookOwner = personDAO.getBookOwner(id);
//        if (bookOwner.isPresent()){
//            model.addAttribute("bookOwner", bookOwner.get());
//        } else {
//            model.addAttribute("people", personDAO.index());
//        }
//        model.addAttribute("book", bookDAO.show(id));
//
//        return "books/show";
//    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
            booksService.save(book);
         return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }




}

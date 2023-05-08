package de.artsem.springcourse.Project2Boot.controllers;


import de.artsem.springcourse.Project2Boot.models.Book;
import de.artsem.springcourse.Project2Boot.models.Person;
import de.artsem.springcourse.Project2Boot.services.BooksService;
import de.artsem.springcourse.Project2Boot.services.PeopleService;
import de.artsem.springcourse.Project2Boot.util.BookValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/sorted_by_year")
    public String indexSortByYear(Model model){
        model.addAttribute("books", booksService.findAllBooksWithSorting("year"));
        return "books/index";
    }

    @GetMapping("/sorted_by_name")
    public String indexSortByName(Model model){
        model.addAttribute("books", booksService.findAllBooksWithSorting("name"));
        return "books/index";
    }

    @GetMapping("/sort/{field}")
    public String indexAllBooksWithSorting(Model model, @PathVariable("field") String field){
        model.addAttribute("books",
                booksService.findAllBooksWithSorting(field));
        return "books/index";
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public String indexAllPeopleWithPagination(Model model, @PathVariable("offset") int offset,
                                               @PathVariable("pageSize") int pageSize ){
        model.addAttribute("books",
                booksService.findAllWithPagination(offset,pageSize));
        System.out.println("Books Pagination");
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable ("id") int id, Model model,
                       @ModelAttribute ("person") Person person){
        Optional<Person> bookOwner = peopleService.findBookOwner(id);
        if (bookOwner.isPresent()){
           model.addAttribute("bookOwner", bookOwner.get());
        } else {
            model.addAttribute("people",peopleService.findAll());
        }

        model.addAttribute("book", booksService.findOne(id));
        return "books/show";
    }

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

    @GetMapping("/search")
    public String search(Model model,
                         @ModelAttribute ("book") Book book){
        return "books/search";
    }

    @PostMapping("/searchResult")
    public String searchPost(@RequestParam String startWith,
                         Model model){

       List<Book> foundBooks = booksService.findAllByNameStartingWith(startWith);

       boolean check = false;
        if (foundBooks.isEmpty()){
            System.out.println("empty list, not found");

            check = true;
            model.addAttribute("check", check);
        } else {

            model.addAttribute("foundBooks",foundBooks);
        }
        return "books/searchResult";
    }

    @PatchMapping("/{id}/assign")
    public String assignPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        booksService.assignPersonToBook(id, person);


        System.out.println("add person to book");
        return "redirect:/books/"+id;
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id){
        booksService.releaseBook(id);

        System.out.println("add person to book");
        return "redirect:/books/"+id;
    }



}

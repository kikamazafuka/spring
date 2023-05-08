package de.artsem.springcourse.Project2Boot.controllers;


import de.artsem.springcourse.Project2Boot.models.Person;
import de.artsem.springcourse.Project2Boot.services.BooksService;
import de.artsem.springcourse.Project2Boot.services.ItemService;
import de.artsem.springcourse.Project2Boot.services.PeopleService;
import de.artsem.springcourse.Project2Boot.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Artur
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ItemService itemService;
//    private final PersonDAO personDAO;
    private final BooksService booksService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, ItemService itemService, BooksService booksService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.itemService = itemService;
//        this.personDAO = personDAO;
        this.booksService = booksService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());

//        itemService.findByItemName("AirPods");
//        itemService.findByOwner(peopleService.findAll().get(0));
//
//        peopleService.test();

//        personDAO.testNPlus1();
        return "people/index";
    }

    @GetMapping("/sort/{field}")
    public String indexAllPeopleWithSorting(Model model, @PathVariable("field") String field){
        model.addAttribute("people",
                peopleService.findAll(field));
        return "people/index";
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public String indexAllPeopleWithPagination(Model model, @PathVariable("offset") int offset,
                                               @PathVariable("pageSize") int pageSize ){
        model.addAttribute("people",
                peopleService.findAllWithPagination(offset,pageSize));
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));

//        model.addAttribute("books", booksService.findAllByBookOwnerId(id));
        model.addAttribute("books", booksService.guessOutOfTime(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        System.out.println("new");
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";


        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}

package de.semart.sprigcourse.controllers;

import de.semart.sprigcourse.dao.PersonDAO;
import de.semart.sprigcourse.models.Person;
import de.semart.sprigcourse.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {


    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.index());

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
//        model.addAttribute("people", personDAO.index());
        model.addAttribute("books", personDAO.getBooksByPersonId(id));

        return "people/show";
    }

    @GetMapping("/new")
//    public String newPerson(@ModelAttribute("person") @Valid Person person,
//                            BindingResult bindingResult){
      public String newPerson(@ModelAttribute("person") Person person){

//        if (bindingResult.hasErrors()){
//            return "people/new";
//        }

        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult ){
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()){
            return "people/new";
        }

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()){
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete( id);
        return "redirect:/people";
    }

}

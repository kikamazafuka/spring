package de.artsem.springcourse.Project2Boot.controllers;

import de.artsem.springcourse.Project2Boot.models.UserAccount;
import de.artsem.springcourse.Project2Boot.services.UserAccountService;
import de.artsem.springcourse.Project2Boot.util.UserAccountValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService userAccountService;
    private final UserAccountValidator userAccountValidator;

    @Autowired
    public AuthController(UserAccountService userAccountService, UserAccountValidator userAccountValidator) {
        this.userAccountService = userAccountService;
        this.userAccountValidator = userAccountValidator;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";

    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("userAccount") UserAccount userAccount){
        System.out.println("registration GET controller method");
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String createAccount(@ModelAttribute("userAccount") @Valid UserAccount userAccount,
                                BindingResult bindingResult){
        System.out.println("registration POST controller method");
        userAccountValidator.validate(userAccount, bindingResult);
        if (bindingResult.hasErrors()){
            return "/auth/registration";
        }
        System.out.println("registration controller POST before save");
        userAccountService.save(userAccount);
        System.out.println("registration controller POST after save");
        return "redirect:/auth/login";
    }
}

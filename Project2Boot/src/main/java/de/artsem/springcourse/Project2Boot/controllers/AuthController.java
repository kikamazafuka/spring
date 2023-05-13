package de.artsem.springcourse.Project2Boot.controllers;

import de.artsem.springcourse.Project2Boot.models.UserAccount;
import de.artsem.springcourse.Project2Boot.services.UserAccountDetailsService;
import de.artsem.springcourse.Project2Boot.services.UserAccountService;
import de.artsem.springcourse.Project2Boot.util.UserAccountValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService userAccountService;
    private final UserAccountValidator userAccountValidator;
    private final UserAccountDetailsService userAccountDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserAccountService userAccountService, UserAccountValidator userAccountValidator,
                          UserAccountDetailsService userAccountDetailsService, PasswordEncoder passwordEncoder) {
        this.userAccountService = userAccountService;
        this.userAccountValidator = userAccountValidator;
        this.userAccountDetailsService = userAccountDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";

    }

//    @PostMapping("/process_login")
//    public String processLogin(@RequestParam("name") String username,
//                               @RequestParam("password") String password,
//                               RedirectAttributes redirectAttributes) {
//        // retrieve the user from the database
//        UserAccount user = (UserAccount) userAccountDetailsService.loadUserByUsername(username);
//
//        // check if the user exists and if the entered password matches the encoded password
//        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            // log the user in and redirect to the home page
//            return "redirect:/";
//        } else {
//            // display an error message and redirect to the login page
//            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
//            return "redirect:/auth/login";
//        }
//    }

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
        userAccountService.registrate(userAccount);
        System.out.println("registration controller POST after save");
        return "redirect:/auth/login";
    }
}

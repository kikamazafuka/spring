package de.artsem.springcourse.Project2Boot.controllers;

import de.artsem.springcourse.Project2Boot.security.UserAccountDetails;
import de.artsem.springcourse.Project2Boot.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final AdminService adminService;

    @Autowired
    public MainController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    public String welcome(){
        return "index";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccountDetails userAccountDetails = (UserAccountDetails)authentication.getPrincipal();
        System.out.println(userAccountDetails.getUserAccount());
        return "index";
    }

    @GetMapping("admin")
    public String adminPage(){
        adminService.doAdminStuff();
        return "admin";
    }
}

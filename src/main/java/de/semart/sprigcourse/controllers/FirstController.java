package de.semart.sprigcourse.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model){
       // String name = httpServletRequest.getParameter("name");
        //String surname = httpServletRequest.getParameter("surname");
        model.addAttribute("message", "Hello, "+ name+ " " + surname);

        System.out.println("Hello, "+ name+ " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(){
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculate(@RequestParam(value = "a", required = false) int a,
                            @RequestParam(value = "b", required = false) int b,
                            @RequestParam(value = "action", required = false) String action,
                            Model model){
        
        int result = 0;
        switch (action){
            case "multiplication":
                 result = a*b;
                break;
            case "addition":
                 result = a+b;
                break;
            case "subtraction":
                result = a-b;
                break;
            case "division":
                result = a/b;
                break;
        }
        model.addAttribute("input", "Input parameters: a="+a +", "+ "b="+b+", action: "+action);
        model.addAttribute("result", "Calculation result: "+result);

        return  "first/calculator";
    }
}

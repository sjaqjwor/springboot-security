package com.example.demo.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @GetMapping({"/","/login"})
    public String loginPage(){
        return "login";
    }
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("user",SecurityContextHolder.getContext().getAuthentication().getName());
        return "lndex";
    }
}

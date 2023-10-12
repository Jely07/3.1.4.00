package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String homePage(){
        return "index";
    }
    @GetMapping("/user")
    public String forUserPage(){
        return "user";
    }
    @GetMapping("/admin")
    public String forAdminPage(){
        return "admin";
    }
}

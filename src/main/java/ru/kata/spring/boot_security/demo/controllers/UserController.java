package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users= userService.findAll();
        model.addAttribute("users",users);
        return "user-list";
    }
    @GetMapping("/user-create")
    public String createUserForm(User user,Model model){
        model.addAttribute("user",user);
        return "user-create";
    }
    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
    @GetMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id")Long id,Model model){
        Optional<User> user;
        user = userService.findById(id);
        model.addAttribute("user",user);
        return "/user-update";
    }

    @PostMapping ("/user-update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
}

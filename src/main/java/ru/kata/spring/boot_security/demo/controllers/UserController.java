package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users= userService.findAll();
        model.addAttribute("users",users);
        return "user-list";
    }
    @GetMapping("/user-create")
    public String createUserForm(User user,Model model){
        List<Role> roles =roleRepository.findAll();
        model.addAttribute("user",user);
        model.addAttribute("roles",roles);
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
        User user = userService.getById(id);
        List<Role> roles =roleRepository.findAll();
        model.addAttribute("user",user);
        model.addAttribute("roles",roles);
        return "/user-update";
    }

    @PostMapping ("/user-update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
}

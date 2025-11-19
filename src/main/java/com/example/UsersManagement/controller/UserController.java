package com.example.UsersManagement.controller;

import com.example.UsersManagement.model.User;
import com.example.UsersManagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/","/list",""})
    public String readAll(Model model,@ModelAttribute User user){
        List< User> users=userService.findAll();
        System.out.println(users);
        model.addAttribute("users",users);
        return "list";
    }

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("user",new User());
        return "form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user){
        if(user.getId()!=null){
            // update
            userService.updateUser(user);
        }else{
            // create
            userService.create(user);
        }
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        System.out.println("id "+id);
        userService.delete(id);
        return "redirect:/list";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,Model model){
        System.out.println("id "+id);
        User existingUser=userService.update(id);
        model.addAttribute("user",existingUser);
        return "form";
    }
}

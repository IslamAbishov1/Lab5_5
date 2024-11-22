package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(@RequestParam(defaultValue = "0") int page, Model model) {
        model.addAttribute("users", userService.getAllUsers(PageRequest.of(page, 10)));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userService.getAllUsers(PageRequest.of(page, 10)).getTotalPages());
        return "user-list";
    }
}

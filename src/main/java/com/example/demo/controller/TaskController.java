package com.example.demo.controller;

import entity.Task;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;
import service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listTasks(@AuthenticationPrincipal User user,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(required = false) String search,
                            @RequestParam(required = false) Long categoryId,
                            Model model) {
        Page<Task> tasks;
        if (search != null && !search.isEmpty()) {
            tasks = taskService.searchTasks(user, search, PageRequest.of(page, 10));
        } else if (categoryId != null) {
            tasks = taskService.filterTasksByCategory(user, categoryId, PageRequest.of(page, 10));
        } else {
            tasks = taskService.getTasksByUser(user, PageRequest.of(page, 10));
        }
        model.addAttribute("tasks", tasks.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", tasks.getTotalPages());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "task-list";
    }
}

package com.deepak.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public String getAllTodos(Model model) {
        List<Todo> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @GetMapping("/{id}")
    public String getTodoById(@PathVariable Long id, Model model) {
        Todo todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @GetMapping("/create")
    public String createTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "create-todo";
    }

    @PostMapping
    public String createTodo(@ModelAttribute Todo todo) {
        todoService.createTodo(todo);
        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String editTodoForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "edit-todo";
    }

    @PostMapping("/edit/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute Todo todo) {
        todoService.updateTodo(id, todo);
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }
}

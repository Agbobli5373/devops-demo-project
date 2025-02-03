package com.deepak.demo;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private List<Todo> todos = new ArrayList<>();

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo getTodoById(Long id) {
        return todos.stream().filter(todo -> todo.getId().equals(id)).findFirst().orElse(null);
    }

    public Todo createTodo(Todo todo) {
        todos.add(todo);
        return todo;
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo todo = getTodoById(id);
        if (todo != null) {
            todo.setTitle(updatedTodo.getTitle());
            todo.setDescription(updatedTodo.getDescription());
        }
        return todo;
    }

    public void deleteTodo(Long id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }
}



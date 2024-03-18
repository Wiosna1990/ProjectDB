package org.example.todo.dao;

import org.example.todo.service.ToDo;

import java.util.List;
import java.util.UUID;

public interface ToDoRepository {
    void save(ToDo todo);
    List<ToDo> getAll();
    void remove(UUID uuid);
}

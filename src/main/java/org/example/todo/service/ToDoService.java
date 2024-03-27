package org.example.todo.service;

import org.example.todo.dao.ToDoRepository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final Clock clock;


    public ToDoService(ToDoRepository toDoRepository, Clock clock) {
        this.toDoRepository = toDoRepository;
        this.clock = clock;
    }

    public void addToDo(String action){
        ToDo todo = new ToDo(UUID.randomUUID(), action, LocalDateTime.now(clock));
        toDoRepository.save(todo);
    }


    public List<ToDo> getToDoList(){

        return toDoRepository.getAll();
    }

    public void removeToDo(UUID uuid){
        toDoRepository.remove(uuid);
    }

}

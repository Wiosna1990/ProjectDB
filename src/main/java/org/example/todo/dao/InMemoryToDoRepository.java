package org.example.todo.dao;

import org.example.todo.service.ToDo;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;

public class InMemoryToDoRepository implements ToDoRepository {

    private final String action;
    private final Clock clock;
    private final UUID uuid;

    public InMemoryToDoRepository(String action, Clock clock, UUID uuid) {
        this.action = action;
        this.clock = clock;
        this.uuid = uuid;
    }


    Map<UUID, ToDo> toDoMap = new LinkedHashMap<>();

    public void save(ToDo todo) {

        toDoMap.put(todo.uuid(), todo);
    }

    public List<ToDo> getAll() {

        return new ArrayList<>(toDoMap.values());
    }

    public void remove(UUID uuid) {

        if (toDoMap.containsKey(uuid)) {
            toDoMap.remove(uuid);
        } else if (uuid == null) {

            throw new NullPointerException();
        } else if (!toDoMap.containsKey(uuid)) {

            throw new NoSuchElementException("Brak elementu o UUID <" + uuid + ">");
        }
    }
}

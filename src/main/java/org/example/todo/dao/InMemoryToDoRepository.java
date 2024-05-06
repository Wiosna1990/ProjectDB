package org.example.todo.dao;
import org.example.todo.service.ToDo;
import java.util.*;

public class InMemoryToDoRepository implements ToDoRepository {

    private final Map<UUID, ToDo> toDoMap = new LinkedHashMap<>();

    public void save(ToDo todo) {

        toDoMap.put(todo.uuid(), todo);
    }

    public List<ToDo> getAll() {

        return List.copyOf(toDoMap.values());
    }

    public void remove(UUID uuid) {
        Objects.requireNonNull(uuid);

        if (toDoMap.remove(uuid) == null) {
            throw new NoSuchElementException("Brak elementu o UUID <" + uuid + ">");
        }
    }


}


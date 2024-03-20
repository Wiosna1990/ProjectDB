package org.example.todo.service;

import org.example.todo.dao.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ToDoServiceTest {

    private Clock clock = Clock.fixed(Instant.parse("2023-03-18T17:17:30.00Z"), ZoneId.of("UTC"));
    private boolean saveExecuted = false;
    private ToDoRepository toDoRepository = new ToDoRepositoryStub();
    private ToDoService toDoService;

    //given
    @BeforeEach
    void setUp(){
        saveExecuted = false;
        toDoService = new ToDoService(toDoRepository, clock);
    }

    @Test
    void shouldSaveToDo(){
        //when
        toDoService.addToDo("zadanie");

        //then
        assertThat(saveExecuted).isTrue();
    }

    @Test
    void shouldRemoveToDo(){
        //when
        toDoService.removeToDo(UUID.fromString("bbcc4621-d88f-4a94-ae2f-b38072bf5087"));

        //then
        assertThat(saveExecuted).isTrue();
    }

    private class ToDoRepositoryStub implements ToDoRepository{


        @Override
        public void save(ToDo todo) {
            assertThat(todo.action()).isEqualTo("zadanie");
            assertThat(todo.additionDateTime()).isEqualTo("2023-03-18T17:17:30.00");
            saveExecuted = true;
        }

        @Override
        public List<ToDo> getAll() {
            return null;
        }

        @Override
        public void remove(UUID uuid) {
            assertThat(uuid).isEqualTo(UUID.fromString("bbcc4621-d88f-4a94-ae2f-b38072bf5087"));
            saveExecuted = true;
        }
    }
}
package org.example.todo.service;


import org.example.todo.dao.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ToDoServiceTest {

    private Clock clock = Clock.fixed(Instant.parse("2023-03-18T17:17:30.00Z"), ZoneId.of("UTC"));
    private boolean saveExecuted = false;
    private ToDoRepository toDoRepository = new ToDoRepositoryStub();
    private ToDoService toDoService;
    private static final ToDo TEST_TODO = new ToDo(
            UUID.fromString("72331291-2088-4FA7-BE89-D2A680FF1B69"),
            "zadanie1",
            LocalDateTime.of(2023,3,18,17,17,30)
    );

    private static final List<ToDo> TEST_TODO_LIST = List.of(TEST_TODO);
    private List<ToDo> toDoList = new ArrayList<>();


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
    void shouldGetAllToDo(){
        //when
        toDoService.viewList();

        //then
        assertThat(toDoList).containsExactly(TEST_TODO);
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

            return TEST_TODO_LIST;

        }

        @Override
        public void remove(UUID uuid) {

        }
    }
}
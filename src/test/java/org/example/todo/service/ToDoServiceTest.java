package org.example.todo.service;

import org.example.todo.dao.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

    private static final ToDo TEST_TODO = new ToDo(
            UUID.fromString("72331291-2088-4FA7-BE89-D2A680FF1B69"),
            "zadanie1",
            LocalDateTime.of(2023,3,18,17,17,30)
    );

    private static final UUID TEST_UUID = UUID.fromString("bbcc4621-d88f-4a94-ae2f-b38072bf5087");

    @Mock
    private ToDoRepository toDoRepository;

    @Captor
    private ArgumentCaptor<ToDo> toDoCaptor;

    private Clock clock = Clock.fixed(Instant.parse("2023-03-18T17:17:30.00Z"), ZoneId.of("UTC"));
    private ToDoService toDoService;


    //given
    @BeforeEach
    void setUp(){
        toDoService = new ToDoService(toDoRepository, clock);
    }

    @Test
    void shouldSaveToDo(){
        //when
        toDoService.addToDo("zadanie");

        //then
        verify(toDoRepository).save(toDoCaptor.capture());
        ToDo todo = toDoCaptor.getValue();
        assertThat(todo.action()).isEqualTo("zadanie");
        assertThat(todo.additionDateTime()).isEqualTo("2023-03-18T17:17:30.00");
        assertThat(todo.uuid()).isNotNull();
    }

    @Test
    void shouldGetAllToDo(){
        //given
        given(toDoRepository.getAll()).willReturn(List.of(TEST_TODO));

        //when
        List<ToDo> toDoList = toDoService.getToDoList();

        //then
        assertThat(toDoList).containsExactly(TEST_TODO);
    }

    @Test
    void shouldRemoveToDo(){
        //when
        toDoService.removeToDo(TEST_UUID);

        //then
        verify(toDoRepository).remove(TEST_UUID);

    }

}

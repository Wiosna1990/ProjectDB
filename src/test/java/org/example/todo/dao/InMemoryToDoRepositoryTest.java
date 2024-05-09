package org.example.todo.dao;

import org.example.todo.service.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class InMemoryToDoRepositoryTest {

    private static final UUID TEST_UUID = UUID.fromString("bbcc4621-d88f-4a94-ae2f-b38072bf5087");
    private static final ToDo TEST_TODO_SAME_UUID1 = new ToDo(
            UUID.fromString("72331291-2088-4FA7-BE89-D2A680FF1B69"),
            "zadanie1",
            LocalDateTime.of(2023, 3, 18, 17, 17, 30)
    );
    private static final ToDo TEST_TODO_SAME_UUID2 = new ToDo(
            UUID.fromString("72331291-2088-4FA7-BE89-D2A680FF1B69"),
            "zadanie2",
            LocalDateTime.of(2024, 3, 18, 17, 17, 30)
    );

    private static final ToDo TEST_TODO3 = new ToDo(
            UUID.fromString("72331291-2088-4FA7-BE89-D2A680FF1B10"),
            "zadanie3",
            LocalDateTime.of(2024, 3, 18, 17, 17, 30)
    );

    private InMemoryToDoRepository inMemoryToDoRepository;

    //Given
    @BeforeEach
    void setUp() {
        inMemoryToDoRepository = new InMemoryToDoRepository();
    }

    @Test
    void givenNullInSaveMethodShouldThrowNullPointerException() {
        //when
        Throwable thrown = catchThrowable(() -> inMemoryToDoRepository.save(null));

        //then
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

    @Test
    void givenToDoShouldReturnNotEmptyList(){
        //when
        inMemoryToDoRepository.save(TEST_TODO_SAME_UUID1);

        //then
        assertThat(inMemoryToDoRepository.getAll()).containsExactly(TEST_TODO_SAME_UUID1);
    }

    @Test
    void givenKnownUuidButDifferentActionAndTimeShouldReplaceToDo() {
        //when
        inMemoryToDoRepository.save(TEST_TODO_SAME_UUID1);
        inMemoryToDoRepository.save(TEST_TODO_SAME_UUID2);

        //then
        assertThat(inMemoryToDoRepository.getAll()).containsExactly(TEST_TODO_SAME_UUID2);
    }

    @Test
    void givenNextToDoShouldReturnListWithMoreElements(){
        //when
        inMemoryToDoRepository.save(TEST_TODO_SAME_UUID1);
        inMemoryToDoRepository.save(TEST_TODO3);

        //then
        assertThat(inMemoryToDoRepository.getAll()).containsExactly(TEST_TODO_SAME_UUID1,TEST_TODO3);
    }


    @Test
    void givenNullInRemoveMethodShouldThrowNullPointerException() {
        //when
        Throwable thrown = catchThrowable(() -> inMemoryToDoRepository.remove(null));

        //then
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

    @Test
    void givenUnknownUuidInRemoveMethodShouldThrowNoSuchElementException() {
        //when
        Throwable thrown = catchThrowable(() -> inMemoryToDoRepository.remove(TEST_UUID));

        //then
        assertThat(thrown).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void givenUuidInRemoveMethodOnListWithOneElementShouldReturnEmptyList(){
        //when
        inMemoryToDoRepository.save(TEST_TODO_SAME_UUID1);
        inMemoryToDoRepository.remove(TEST_TODO_SAME_UUID1.uuid());

        //then
        assertThat(inMemoryToDoRepository.getAll()).isEmpty();
    }

    @Test
    void givenUuidInRemoveMethodOnListWithMoreElementsShouldReturnNotEmptyLists(){
        //when
        inMemoryToDoRepository.save(TEST_TODO_SAME_UUID1);
        inMemoryToDoRepository.save(TEST_TODO3);
        inMemoryToDoRepository.remove(TEST_TODO_SAME_UUID1.uuid());

        //then
        assertThat(inMemoryToDoRepository.getAll()).containsExactly(TEST_TODO3);
    }

    @Test
    void givenListIsNotNull() {
        //when
        List<ToDo> result = inMemoryToDoRepository.getAll();

        //then
        assertThat(result)
                .isNotNull()
                .isEmpty();
    }


}
package org.example.todo.dao;

import org.example.todo.service.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class InMemoryToDoRepositoryTest {

    private static final UUID TEST_UUID = UUID.fromString("bbcc4621-d88f-4a94-ae2f-b38072bf5087");
    private static final ToDo TEST_TODO = new ToDo(
            UUID.fromString("72331291-2088-4FA7-BE89-D2A680FF1B69"),
            "zadanie1",
            LocalDateTime.of(2023, 3, 18, 17, 17, 30)
    );
    private static final Map<UUID, ToDo> TEST_MAP = new LinkedHashMap<UUID, ToDo>() {{
        put(UUID.fromString("72331291-2088-4FA7-BE89-D2A680FF1B69"),
                new ToDo(UUID.fromString("72331291-2088-4FA7-BE89-D2A680FF1B69"),
                        "zadanie2",
                        LocalDateTime.of(2024, 3, 18, 17, 17, 30)));

    }};

    private Clock clock = Clock.fixed(Instant.parse("2023-03-18T17:17:30.00Z"), ZoneId.of("UTC"));
    private UUID uuid = UUID.fromString("aacc4621-d88f-4a94-ae2f-b38072bf5087");
    private String action = "zadanie1";
    private InMemoryToDoRepository inMemoryToDoRepository;

    //Given
    @BeforeEach
    void setUp() {
        inMemoryToDoRepository = new InMemoryToDoRepository(action, clock, uuid);
    }

    @Test
    void givenNullInSaveMethodShouldReturnNullPointerException() {
        //when
        Throwable thrown = catchThrowable(() -> inMemoryToDoRepository.save(null));

        //then
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

    @Test
    void givenKnownUuidButDifferentActionAndTimeShouldReplaceToDo() {
        //when
        inMemoryToDoRepository.save(TEST_TODO);

        TEST_MAP.put(TEST_TODO.uuid(), TEST_TODO);

        //then
        assertThat(TEST_MAP).contains(Map.entry(TEST_TODO.uuid(), TEST_TODO));
    }


    @Test
    void givenNullInRemoveMethodShouldReturnNullPointerException() {
        //when
        Throwable thrown = catchThrowable(() -> inMemoryToDoRepository.remove(null));

        //then
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }


    @Test
    void givenUnknownUuidInRemoveMethodShouldReturnNoSuchElementException() {
        //when
        Throwable thrown = catchThrowable(() -> inMemoryToDoRepository.remove(TEST_UUID));

        //then
        assertThat(thrown).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void givenListIsNotNull() {
        //when
        List<ToDo> result = inMemoryToDoRepository.getAll();

        //then
        assertThat(result).isNotNull();
    }


}
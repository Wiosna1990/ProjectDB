package org.example.todo.service;

import java.time.LocalDateTime;
import java.util.UUID;

public record ToDo(UUID uuid,String action, LocalDateTime additionDateTime) {
}


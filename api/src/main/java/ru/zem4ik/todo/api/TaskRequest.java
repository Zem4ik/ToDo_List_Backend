package ru.zem4ik.todo.api;

import lombok.Data;

@Data
public class TaskRequest {
    private long listId;
    private String title;
}

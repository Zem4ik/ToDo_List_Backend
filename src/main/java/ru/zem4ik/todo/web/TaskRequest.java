package ru.zem4ik.todo.web;

import lombok.Data;

@Data
public class TaskRequest {
    private long listId;
    private String title;
}

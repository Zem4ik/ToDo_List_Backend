package ru.zem4ik.todo.data.lists;

import ru.zem4ik.todo.domain.TasksList;

public class ListUtils {
    private static int nextListId = 0;

    final static String NAME = "LIST_NAME";

    public static TasksList createDefaultList() {
        TasksList tasksList = new TasksList(NAME);
        tasksList.setIcon(null);
        return tasksList;
    }

    public static TasksList generateNext() {
        return createListById(nextListId++, null);
    }

    public static TasksList createListById(int id, String iconPath) {
        TasksList tasksList = new TasksList(NAME + id);
        tasksList.setIcon(iconPath);
        return tasksList;
    }
}

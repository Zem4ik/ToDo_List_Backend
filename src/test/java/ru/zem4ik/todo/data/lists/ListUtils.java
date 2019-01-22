package ru.zem4ik.todo.data.lists;

import ru.zem4ik.todo.domain.List;

public class ListUtils {
    private static int nextListId = 0;

    final static String NAME = "LIST_NAME";

    public static List createDefaultList() {
        List list = new List(NAME);
        list.setIcon(null);
        return list;
    }

    public static List generateNext() {
        return createListById(nextListId++, null);
    }

    public static List createListById(int id, byte[] icon) {
        List list = new List(NAME + id);
        list.setIcon(icon);
        return list;
    }
}

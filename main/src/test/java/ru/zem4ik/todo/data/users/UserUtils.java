package ru.zem4ik.todo.data.users;

import ru.zem4ik.todo.domain.User;

public class UserUtils {
    private static int nextUserId = 0;

    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
    private final static String NAME = "name";
    private final static String SURNAME = "surname";
    private final static String EMAIL = "email";

    public static User createDefaultUser() {
        return new User(USERNAME,
                PASSWORD);
    }

    public static User generateNext() {
        return createUserById(nextUserId++, null);
    }

    public static User createUserById(int id, String imagePath) {
        return createUserByIds(id, id, id, id, id, imagePath);
    }

    public static User createUserByIds(int userNameId,
                                       int passwordId,
                                       int nameId,
                                       int surnameId,
                                       int emailId,
                                       String imagePath) {
        User user = new User(USERNAME + userNameId,
                PASSWORD + passwordId);
        user.setName(NAME + nameId);
        user.setUsername(SURNAME + surnameId);
        user.setEmail(EMAIL + emailId);
        user.setImage(imagePath);
        return user;
    }

}

package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(null,"Timur", "timdj01@gmail.com", "timdzha", Role.ROLE_USER, Role.ROLE_ADMIN),
            new User(null,"Saga", "saga01@gmail.com", "saga", Role.ROLE_USER));
}

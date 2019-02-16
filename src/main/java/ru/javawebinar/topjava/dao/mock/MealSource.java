package ru.javawebinar.topjava.dao.mock;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс эмуляции базы данных питания
 */
public class MealSource {
    private static MealSource _instance = new MealSource();

    public static MealSource getInstance() {
        return _instance;
    }

    private MealSource() {

    }

    private List<Meal> meals = Arrays.asList(
        new Meal(LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "Завтрак", 500),
        new Meal(LocalDateTime.of(2018, Month.MAY, 30, 13, 0), "Обед", 1000),
        new Meal(LocalDateTime.of(2018, Month.MAY, 30, 20, 0), "Ужин", 500),
        new Meal(LocalDateTime.of(2018, Month.MAY, 31, 10, 0), "Завтрак", 1000),
        new Meal(LocalDateTime.of(2018, Month.MAY, 31, 13, 0), "Обед", 500),
        new Meal(LocalDateTime.of(2018, Month.MAY, 31, 20, 0), "Ужин", 510),
        new Meal(LocalDateTime.of(2018, Month.MAY, 29, 10, 0), "Завтрак", 1000),
        new Meal(LocalDateTime.of(2018, Month.MAY, 29, 13, 0), "Обед", 1000),
        new Meal(LocalDateTime.of(2018, Month.MAY, 29, 20, 0), "Ужин", 1010)
    );

    public List<Meal> getMeals() {
        return meals;
    }
}

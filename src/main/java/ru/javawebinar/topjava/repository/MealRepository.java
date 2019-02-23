package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Sort;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface MealRepository {
    Meal save(int userId, Meal meal);

    boolean delete(int userId, int id);

    Meal get(int userId, int id);

    Stream<Meal> getAll(int userId);

    Stream<Meal> getFilteredByDateAndSorted(int userId, LocalDate startDate, LocalDate endDate, Sort sorting);
}

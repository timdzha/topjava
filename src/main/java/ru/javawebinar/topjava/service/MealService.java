package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Sort;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface MealService {

    Meal create(int userId, Meal meal);

    void delete(int userId, int id) throws NotFoundException;

    Meal get(int userId, int id) throws NotFoundException;

    void update(int userId, Meal meal);

    Stream<Meal> getAll(int userId);

    Stream<Meal> getFilteredByDate(int userId, LocalDate startDate, LocalDate endDate, Sort sorting);
}
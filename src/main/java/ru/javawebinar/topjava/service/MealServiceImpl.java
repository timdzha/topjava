package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Sort;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.stream.Stream;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal create(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void delete(int userId, int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(userId, id), id);
    }

    @Override
    public Meal get(int userId, int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(userId, id), id);
    }

    @Override
    public void update(int userId, Meal meal) throws NotFoundException {
        checkNotFoundWithId(repository.save(userId, meal), meal.getId());
    }

    @Override
    public Stream<Meal> getAll(int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.getAll(userId), userId);
    }

    public Stream<Meal> getFilteredByDate(int userId, LocalDate startDate, LocalDate endDate, Sort sorting) throws NotFoundException {
        return checkNotFoundWithId(repository.getFilteredByDateAndSorted(userId, startDate, endDate, sorting), userId);
    }
}
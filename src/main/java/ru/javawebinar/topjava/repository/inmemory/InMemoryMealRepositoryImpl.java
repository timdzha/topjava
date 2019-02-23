package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Sort;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static ru.javawebinar.topjava.model.Sort.*;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.TIMUR_MEALS.forEach(meal -> save(1, meal));
        MealsUtil.SAGA_MEALS.forEach(meal -> save(2, meal));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        repository.putIfAbsent(userId, new HashMap<>());

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        return repository.getOrDefault(userId, new HashMap<>()).remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        return repository.getOrDefault(userId, new HashMap<>()).getOrDefault(id, null);
    }

    @Override
    public Stream<Meal> getAll(int userId) {
        return getSortedDesc(getByUserId(userId));
    }

    public Stream<Meal> getFilteredByDateAndSorted(int userId, LocalDate startDate, LocalDate endDate, Sort sorting) {
        Stream<Meal> stream = getFilteredByDate(
                getByUserId(userId),
                meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate));

        switch (sorting) {
            case ASC:
                return getSortedAsc(stream);
            case DESC:
                return getSortedDesc(stream);
            case NONE:
            default:
                return stream;
        }
    }

    public Stream<Meal> getByUserId(int userId) {
        return repository.getOrDefault(userId, new HashMap<>()).values().stream();
    }

    private Stream<Meal> getFilteredByDate(Stream<Meal> streamToFilter, Predicate<Meal> filter) {
        return streamToFilter.filter(filter);
    }

    private Stream<Meal> getSortedAsc(Stream<Meal> streamToSort) {
        return streamToSort.sorted(Comparator.comparing(Meal::getDateTime));
    }

    private Stream<Meal> getSortedDesc(Stream<Meal> streamToSort) {
        return streamToSort.sorted(Comparator.comparing(Meal::getDateTime).reversed());
    }
}


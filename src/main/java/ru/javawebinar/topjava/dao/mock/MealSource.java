package ru.javawebinar.topjava.dao.mock;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Класс эмуляции базы данных питания
 */
public class MealSource {
    private static MealSource _instance = new MealSource();

    public static MealSource getInstance() {
        return _instance;
    }

    private Map<Integer, Meal> mealsWithIds = new ConcurrentHashMap();
    private AtomicInteger counter = new AtomicInteger(0);

    private MealSource() {
        generateMeals();
    }

    private void generateMeals() {
        createOrUpdate(new Meal(LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "Завтрак", 500));
        createOrUpdate(new Meal(LocalDateTime.of(2018, Month.MAY, 30, 13, 0), "Обед", 1000));
        createOrUpdate(new Meal(LocalDateTime.of(2018, Month.MAY, 30, 20, 0), "Ужин", 500));
        createOrUpdate(new Meal(LocalDateTime.of(2018, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        createOrUpdate(new Meal(LocalDateTime.of(2018, Month.MAY, 31, 13, 0), "Обед", 500));
        createOrUpdate(new Meal(LocalDateTime.of(2018, Month.MAY, 31, 20, 0), "Ужин", 510));
        createOrUpdate(new Meal(LocalDateTime.of(2018, Month.MAY, 29, 10, 0), "Завтрак", 1100));
        createOrUpdate(new Meal(LocalDateTime.of(2018, Month.MAY, 29, 13, 0), "Обед", 500));
        createOrUpdate(new Meal(LocalDateTime.of(2018, Month.MAY, 29, 20, 0), "Ужин", 1010));
    }

    private int generateId() {
        return counter.incrementAndGet();
    }

    private Meal generateNullMeal() {
        return new Meal(LocalDateTime.now(), "NULL_MEAL", 0);
    }

    public Meal createOrUpdate(Meal newMeal) {
        if (newMeal == null) return generateNullMeal();
        if (newMeal.getId() != 0)
            return mealsWithIds.put(mealsWithIds.get(newMeal.getId()).getId(), newMeal);

        newMeal.setId(generateId());
        return mealsWithIds.put(newMeal.getId(), newMeal);
    }

    public Meal delete(int id) {
        return id == 0 ? generateNullMeal() : mealsWithIds.remove(id);
    }

    public List<Meal> getMeals() {
        return mealsWithIds.values().stream().collect(Collectors.toList());
    }
}

package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.dao.mock.MealSource;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class MealDao {
    private MealSource mealSource = MealSource.getInstance();

    public Meal save(Meal meal) {
        return mealSource.createOrUpdate(meal);
    }

    public Meal deleteById(int id) {
        return mealSource.delete(id);
    }
    public Meal getMealById(int id) {
       return mealSource.getMeals().get(id-1);
    }

    public List<Meal> getMeals() {
        return mealSource.getMeals();
    }


}

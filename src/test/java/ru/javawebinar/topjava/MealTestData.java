package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 8;

    public static final Meal MEAL1 = new Meal(MEAL_ID, LocalDateTime.of(2019, Month.FEBRUARY, 27, 10, 0), "Завтрак", 1000);
    public static final Meal MEAL2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2019, Month.FEBRUARY, 27, 13, 0), "Обед", 500);
    public static final Meal MEAL3 = new Meal(MEAL_ID + 2, LocalDateTime.of(2019, Month.FEBRUARY, 27, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(MEAL_ID + 3, LocalDateTime.of(2019, Month.FEBRUARY, 28, 10, 0), "Завтрак", 500);
    public static final Meal MEAL5 = new Meal(MEAL_ID + 4, LocalDateTime.of(2019, Month.FEBRUARY, 28, 13, 0), "Обед", 1000);
    public static final Meal MEAL6 = new Meal(MEAL_ID + 5, LocalDateTime.of(2019, Month.FEBRUARY, 28, 20, 0), "Ужин", 1000);
    public static final Meal ADMIN_MEAL1 = new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2019, Month.JANUARY, 28, 15, 0), "Обед", 1000);
    public static final Meal ADMIN_MEAL2 = new Meal(ADMIN_MEAL_ID + 1, LocalDateTime.of(2019, Month.JANUARY, 28, 20, 0), "Ужин", 1000);

    public static final List<Meal> MEALS = Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static Meal getCreated() {
        return new Meal(null,LocalDateTime.of(2019, Month.MARCH, 1, 10, 0), "Созданный Завтрак", 1000);
    }

    public static Meal getCreatedWithTheSameDateTime() {
        return new Meal(null,LocalDateTime.of(2019, Month.FEBRUARY, 27, 20, 0), "Созданный Ужин", 1000);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1.getId(), MEAL1.getDateTime(), "Обновленный Завтрак", 500);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

}

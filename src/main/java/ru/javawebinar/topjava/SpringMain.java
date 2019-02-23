package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.MealServlet;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
//            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            MealServlet mealServlet = new MealServlet();
//            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
//            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));
//            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
//            adminUserController.getAll().forEach(user -> System.out.println(user));
//            SecurityUtil.setAuthUserId(2);
//            mealRestController.getAll().forEach(meal -> System.out.println(meal));


//            mealRestController.delete(10);
//            mealRestController.update(new Meal(6, LocalDateTime.of(2019, Month.JANUARY, 12, 10, 0), "Завтрак", 500));
//            System.out.println(mealRestController.get(6));
//            mealRestController.create(new Meal(LocalDateTime.of(2019, Month.FEBRUARY, 12, 10, 0), "Завтрак", 500));
//            mealRestController.update(new Meal(10, LocalDateTime.of(2019, Month.JANUARY, 12, 10, 0), "Завтрак", 500));
//            mealRestController.update(new Meal(8, LocalDateTime.of(2019, Month.JANUARY, 12, 10, 0), "Завтрак", 500));
//            mealRestController.get(1);
//            mealRestController.delete(1);
//            mealRestController.getAll().forEach(meal -> System.out.println(meal));
        }
    }
}

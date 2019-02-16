package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.mock.MealSource;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final MealDao mealDao = new MealDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = getId(request);
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime"))
                ,request.getParameter("description")
                ,Integer.valueOf(request.getParameter("calories"))
                );
        meal.setId(id);
        mealDao.save(meal);
        log.debug(id == 0 ? "Created {}" : "Updated {}", meal);
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = getAction(request);

        switch (action) {
            case "create" :
                Meal newMeal = new Meal(LocalDateTime.now(), "", 0);
                request.setAttribute("meal", newMeal);
                request.getRequestDispatcher("/meals-edit.jsp").forward(request, response);
                break;
            case "update" :
                Meal updatedMeal = mealDao.getMealById(getId(request));
                request.setAttribute("meal", updatedMeal);
                request.getRequestDispatcher("/meals-edit.jsp").forward(request, response);
                break;

            case "delete" :
                int id = getId(request);
                log.debug("Deleted {}", mealDao.deleteById(id));
                response.sendRedirect("meals");
                break;
            default :
                log.debug("list meals");
                request.setAttribute("meals", MealsUtil.getWithExcess(mealDao.getMeals(), 2000));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
            return Integer.parseInt(request.getParameter("id"));
    }

    private String  getAction(HttpServletRequest request) {
        try {
            return Objects.requireNonNull(request.getParameter("action"));
        } catch (NullPointerException e) {
            return  "";
        }
    }

}

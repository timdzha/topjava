package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.web.SecurityUtil.getAuthUserId;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private static boolean isFiltered = false;
    private LocalDate[] dateFilters;
    private LocalTime[] timeFilters;
    private static final MealRestController controller;
    private static final ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");

    static {
        controller = appCtx.getBean(MealRestController.class);
    }

    @Override
    public void destroy() {
        appCtx.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("userId") != null)
            SecurityUtil.setAuthUserId(Integer.parseInt(request.getParameter("userId")));
        else if (request.getParameter("filter") != null) {
            dateFilters = getDateFilters(request);
            timeFilters = getTimeFilters(request);
        }
        else {
            String id = request.getParameter("id");
            Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories")));

            if (meal.isNew())
                controller.create(meal);
            else controller.update(getId(request), meal);
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                controller.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        controller.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                if (dateFilters != null || timeFilters != null) {
                    request.setAttribute("meals", controller.getFilteredByDateTime(dateFilters[0], dateFilters[1], timeFilters[0], timeFilters[1]));
                    if (dateFilters[0] != LocalDate.MIN) request.setAttribute("fromDate", dateFilters[0]);
                    if (dateFilters[1] != LocalDate.MAX) request.setAttribute("toDate", dateFilters[1]);
                    if (timeFilters[0] != LocalTime.MIN) request.setAttribute("fromTime", timeFilters[0]);
                    if (timeFilters[1] != LocalTime.MAX) request.setAttribute("toTime", timeFilters[1]);
                }
                else request.setAttribute("meals", controller.getAll());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    private LocalDate[] getDateFilters(HttpServletRequest request) {
        String[] dateInterval = {request.getParameter("fromDate"), request.getParameter("toDate")};
        LocalDate startDate = LocalDate.MIN;
        LocalDate endDate = LocalDate.MAX;
        if (!dateInterval[0].isEmpty())
            startDate = LocalDate.parse(dateInterval[0]);
        if (!dateInterval[1].isEmpty())
            endDate = LocalDate.parse(dateInterval[1]);

        return new LocalDate[]{startDate, endDate};
    }
    private LocalTime[] getTimeFilters(HttpServletRequest request) {
        String[] timeInterval = {request.getParameter("fromTime"), request.getParameter("toTime")};
        LocalTime startTime = LocalTime.MIN;
        LocalTime endTime = LocalTime.MAX;
        if (!timeInterval[0].isEmpty())
            startTime = LocalTime.parse(timeInterval[0]);
        if (!timeInterval[1].isEmpty())
            endTime = LocalTime.parse(timeInterval[1]);
        return new LocalTime[]{startTime, endTime};
    }
}

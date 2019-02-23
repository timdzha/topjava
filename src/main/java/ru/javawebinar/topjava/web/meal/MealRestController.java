package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Sort;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.web.SecurityUtil.getAuthUserId;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    /*
5.7: Сделайте отдельный getAll без применения фильтра
6: Проверьте корректную обработку пустых значений date и time в контроллере
     */
    public List<MealTo> getAll() {
        log.info("getAll");
        return MealsUtil.getWithExcess(
                service.getAll(getAuthUserId()).collect(Collectors.toList()),
                MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealTo> getFilteredByDateTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("getFilteredByDateTime {} {} - {} {}", startDate, startTime, endDate, endTime);
        return MealsUtil.getFilteredWithExcess(
                service.getFilteredByDate(getAuthUserId(), startDate, endDate, Sort.DESC).collect(Collectors.toList()),
                MealsUtil.DEFAULT_CALORIES_PER_DAY,
                startTime,
                endTime);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(getAuthUserId(), id);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        return service.create(getAuthUserId(), meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(getAuthUserId(), id);
    }

    public void update(int id, Meal meal) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(getAuthUserId(), meal);
    }

}
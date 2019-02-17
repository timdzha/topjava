package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.web.SecurityUtil.getAuthUserId;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Collection<Meal> getAll() {
        log.info("getAll");
        return service.getAll(getAuthUserId());
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

    public void update(Meal meal) {
        log.info("update {}", meal);
        service.update(getAuthUserId(), meal);
    }
}
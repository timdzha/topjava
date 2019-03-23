package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController {

    @GetMapping
    public String list(Model model) {
        model.addAttribute("meals", super.getAll());
        return "meals";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable int id) {
        super.delete(id);
        return "redirect:/meals";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
        Meal meal = super.get(id);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping("/create")
    public String add(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping("/save")
    public String save(@RequestParam String id, @RequestParam String dateTime, @RequestParam String description, @RequestParam String calories) {
        Meal meal = new Meal(LocalDateTime.parse(dateTime.subSequence(0, dateTime.length())), description, Integer.parseInt(calories));
        if (id.isEmpty()) super.create(meal);
        else  {
            meal.setId(Integer.parseInt(id));
            super.update(meal, meal.getId());
        }
        return "redirect:/meals";
    }

    @PostMapping("/filter")
    public String filter(Model model, @RequestParam String startDate, @RequestParam String endDate, @RequestParam String startTime, @RequestParam String endTime) {
        model.addAttribute("meals", super.getBetween(parseLocalDate(startDate), parseLocalTime(startTime), parseLocalDate(endDate), parseLocalTime(endTime)));
        return "meals";
    }
}

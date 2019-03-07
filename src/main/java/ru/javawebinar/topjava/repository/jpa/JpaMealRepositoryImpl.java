package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
//        User user = em.find(User.class, userId);
        User user = em.getReference(User.class, userId);
        if (user == null) throw new IllegalArgumentException("Такой userId не существует!");
        meal.setUser(user);

        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        }

        Meal mealFound = em.find(Meal.class, meal.getId());
        if (mealFound.getUser().getId() == user.getId())
            return em.merge(meal);
        else throw new NotFoundException("Чужую еду нельзя изменить");
    }

    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        /**
         * у EntityManager-a
         * *  метод getSingleResult() кидается эксепшнами в любой ситуации,
         * * если мы не получаем единственный ожидаемый результат. А мы ждем или результат, или null.
         *
         * Метод singleResult класса DataAccessUtils покрывает как раз все наши ожидания для теста getNotFound
         */
        List<Meal> meals = em.createNamedQuery(Meal.BY_ID)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getResultList();
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.DATE_BETWEEN, Meal.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}
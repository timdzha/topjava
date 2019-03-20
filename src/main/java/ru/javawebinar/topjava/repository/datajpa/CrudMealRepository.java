package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    Meal findByIdAndUserId(int id, int userId);

    @Transactional // or else in delete test spring exception occurs "No EntityManager with actual transaction available for current thread"
    int deleteByIdAndUserId(int id, int userId);

    List<Meal> findAllByUserIdOrderByDateTimeDesc(int userId);

    List<Meal> findAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT m FROM Meal m JOIN FETCH m.user WHERE m.id = ?1 AND m.user.id = ?2" )
    Meal getWithUser(int id, int userId);
}

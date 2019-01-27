package ru.lab729.itpir.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.Meal;
import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudOperatorRepository extends JpaRepository<OperatorEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Modifying
    @Transactional
    //@Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int deleteAllByUserId (User user); //(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    @Query("DELETE FROM OperatorEntity o ")
    void deleteAll();

    @Override
    @Transactional
    OperatorEntity save(OperatorEntity item);

    @Query("SELECT o FROM OperatorEntity o WHERE o.user.id=:userId ORDER BY o.operator ASC")
    List<Meal> getAll(@Param("userId") int userId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT m from Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<Meal> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT m FROM Meal m JOIN FETCH m.user WHERE m.id = ?1 and m.user.id = ?2")
    Meal getWithUser(int id, int userId);
}
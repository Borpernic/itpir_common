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
    @Query("DELETE FROM OperatorEntity o WHERE o.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    //@Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int deleteAllByUserId (User user); //(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    @Query("DELETE FROM OperatorEntity o")
    void deleteAll();

    @Override
    @Transactional
    OperatorEntity save(OperatorEntity item);

    @Query("SELECT o FROM OperatorEntity o WHERE o.id=:id ORDER BY o.operator ASC")
    List<OperatorEntity> get(@Param("id") int id);

    @Query("SELECT o FROM OperatorEntity o  ORDER BY o.operator  ASC")
    List<OperatorEntity> getAll();

    @Query("SELECT o FROM OperatorEntity o where o.user.id=:userId ORDER BY o.operator  ASC")
    List<OperatorEntity> getAllByUserId(@Param("userId") int userId);

    @Query("SELECT o FROM OperatorEntity o JOIN FETCH o.user WHERE o.id = ?1 and o.user.id = ?2")
    OperatorEntity getWithUser(int id, int userId);
}
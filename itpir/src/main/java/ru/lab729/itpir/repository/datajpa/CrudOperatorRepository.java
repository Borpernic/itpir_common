package ru.lab729.itpir.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.model.User;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudOperatorRepository extends JpaRepository<OperatorEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM OperatorEntity o WHERE o.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM OperatorEntity o WHERE o.id=:id AND o.user.id=:userId")
    int deleteByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    void deleteAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM OperatorEntity o WHERE o.user.id=:userId")
    int deleteAllByUserId(@Param("userId") int userId);

    @Override
    @Transactional
    OperatorEntity save(OperatorEntity item);

    88888888888888
    @Transactional
    @Modifying
    OperatorEntity saveWithUserId(OperatorEntity operator, int userId);

    @Override
    Optional<OperatorEntity> findById(Integer id);

    @Query("SELECT o FROM OperatorEntity o WHERE o.id=:id AND o.user.id=:userId")
    List<OperatorEntity> getByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT o FROM OperatorEntity o  ORDER BY o.operator  ASC")
    List<OperatorEntity> getAll();

    @Query("SELECT o FROM OperatorEntity o where o.user.id=:userId ORDER BY o.operator  ASC")
    List<OperatorEntity> getAllByUserId(@Param("userId") int userId);

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"siteEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT o FROM OperatorEntity o WHERE o.id=?1")
    User getWithMeals(int id);
}
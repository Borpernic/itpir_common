package ru.lab729.itpir.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.OperatorEntity;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudOperatorRepository extends JpaRepository<OperatorEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM OperatorEntity e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM OperatorEntity e WHERE e.id=:id AND e.user.id=:userId")
    int deleteByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    void deleteAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM OperatorEntity e ")
    int deleteAllEntity();


    @Modifying
    @Transactional
    @Query("DELETE FROM OperatorEntity e WHERE e.user.id=:userId")
    int deleteAllByUserId(@Param("userId") int userId);

    @Override
    @Transactional
    OperatorEntity save(OperatorEntity item);

    /*
    @Transactional
    @Modifying
    OperatorEntity save(OperatorEntity operator, int userId);*/

    @Override
    Optional<OperatorEntity> findById(Integer id);

    @Query("SELECT e FROM OperatorEntity e WHERE e.id=:id AND e.user.id=:userId")
    List<OperatorEntity> get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT e FROM OperatorEntity e  ORDER BY e.operator  ASC")
    List<OperatorEntity> getAll();

    @Query("SELECT e FROM OperatorEntity e where e.user.id=:userId ORDER BY e.operator  ASC")
    List<OperatorEntity> getAll(@Param("userId") int userId);

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"siteEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM OperatorEntity e  JOIN FETCH e.user  WHERE e.id=?1 and e.user.id=?2")
    OperatorEntity getWithUser(int id, int userId);


}
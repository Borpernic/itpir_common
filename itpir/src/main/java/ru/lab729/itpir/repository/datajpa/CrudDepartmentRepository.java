package ru.lab729.itpir.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.DepartmentEntity;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudDepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM DepartmentEntity e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM DepartmentEntity e WHERE e.id=:id AND e.user.id=:userId")
    int deleteByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    void deleteAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM DepartmentEntity e ")
    int deleteAllEntity();


    @Modifying
    @Transactional
    @Query("DELETE FROM DepartmentEntity e WHERE e.user.id=:userId")
    int deleteAllByUserId(@Param("userId") int userId);

    @Override
    @Transactional
    DepartmentEntity save(DepartmentEntity item);

    /*
    @Transactional
    @Modifying
    OperatorEntity save(OperatorEntity operator, int userId);*/

    @Override
    Optional<DepartmentEntity> findById(Integer id);

    @Query("SELECT e FROM DepartmentEntity e WHERE e.id=:id AND e.user.id=:userId")
    List<DepartmentEntity> get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT e FROM DepartmentEntity e  ORDER BY e.department  ASC")
    List<DepartmentEntity> getAll();

    @Query("SELECT e FROM DepartmentEntity e where e.user.id=:userId ORDER BY e.department  ASC")
    List<DepartmentEntity> getAll(@Param("userId") int userId);

    //    https://stackoverflow.com/a/46013654/548473
    // @EntityGraph(attributePaths = {"siteEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM DepartmentEntity e  JOIN FETCH e.user  WHERE e.id=?1 and e.user.id=?2")
    DepartmentEntity getWithUser(int id, int userId);


}
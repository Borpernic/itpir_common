package ru.lab729.itpir.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.model.RegionEntity;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRegionRepository extends JpaRepository<RegionEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM RegionEntity r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM RegionEntity r WHERE r.id=:id AND r.user.id=:userId")
    int deleteByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    void deleteAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM RegionEntity r ")
    int deleteAllEntity();


    @Modifying
    @Transactional
    @Query("DELETE FROM RegionEntity r WHERE r.user.id=:userId")
    int deleteAllByUserId(@Param("userId") int userId);

    @Override
    @Transactional
    RegionEntity save(RegionEntity item);

    /*
    @Transactional
    @Modifying
    OperatorEntity save(OperatorEntity operator, int userId);*/

    @Override
    Optional<RegionEntity> findById(Integer id);

    @Query("SELECT r FROM RegionEntity r WHERE r.id=:id AND r.user.id=:userId")
    List<RegionEntity> get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT r FROM RegionEntity r  ORDER BY r.region  ASC")
    List<RegionEntity> getAll();

    @Query("SELECT r FROM RegionEntity r where r.user.id=:userId ORDER BY r.region  ASC")
    List<RegionEntity> getAll(@Param("userId") int userId);

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"siteEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM RegionEntity r  JOIN FETCH r.user  WHERE r.id=?1 and r.user.id=?2")
    RegionEntity getWithUser(int id, int userId);


}
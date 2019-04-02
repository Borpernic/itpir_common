package ru.lab729.itpir.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.SiteEntity;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudSiteRepository extends JpaRepository<SiteEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM SiteEntity e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM SiteEntity e WHERE e.id=:id AND e.user.id=:userId")
    int deleteByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    void deleteAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM SiteEntity e ")
    int deleteAllEntity();

    @Modifying
    @Transactional
        //@Query("DELETE FROM SiteEntity e WHERE e.user.id=:userId")
    int deleteAllByOperator(int operatorId);

    @Modifying
    @Transactional
        //@Query("DELETE FROM SiteEntity e WHERE e.user.id=:userId")
    int deleteAllByRegion(int regionId);

    @Modifying
    @Transactional
        //@Query("DELETE FROM SiteEntity e WHERE e.user.id=:userId")
    int deleteAllByCommentsContains(String comments);

    @Modifying
    @Transactional
    @Query("DELETE FROM SiteEntity e WHERE e.user.id=:userId")
    int deleteAllByUserId(@Param("userId") int userId);

    @Modifying
    @Transactional
        //@Query("DELETE FROM SiteEntity e WHERE e.user.id=:userId")
    int deleteAllByOperatorAndUserId(int operatorId, int userId);

    @Modifying
    @Transactional
        //@Query("DELETE FROM SiteEntity e WHERE e.user.id=:userId")
    int deleteAllByRegionAndUserId(int regionId, int userId);

    @Modifying
    @Transactional
        //@Query("DELETE FROM SiteEntity e WHERE e.user.id=:userId")
    int deleteAllByCommentsContainsAndUserId(String comments, int userId);

    @Override
    @Transactional
    SiteEntity save(SiteEntity item);

    /*
    @Transactional
    @Modifying
    OperatorEntity save(OperatorEntity operator, int userId);*/

    @Override
    Optional<SiteEntity> findById(Integer id);

    @Query("SELECT e FROM SiteEntity e JOIN FETCH e.operator,  e.region WHERE e.id=:id AND e.user.id=:userId")
    List<SiteEntity> get(@Param("id") int id, @Param("userId") int userId);

    @EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM SiteEntity e JOIN FETCH e.operator,  e.region , e.osEntities WHERE e.id=:id AND e.user.id=:userId")
    List<SiteEntity> getWithOs(@Param("id") int id, @Param("userId") int userId);

    @EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM SiteEntity e JOIN FETCH e.operator,  e.region , e.osEntities WHERE e.id=:id")
    List<SiteEntity> getWithOs(@Param("id") int id);

    @Query("SELECT e FROM SiteEntity e  JOIN FETCH e.operator,  e.region ORDER BY e.operator.operator , e.number  ASC")
    List<SiteEntity> getAll();

    @Query("SELECT e FROM SiteEntity e JOIN FETCH e.operator,  e.region where e.user.id=:userId ORDER BY e.operator.operator , e.number  ASC")
    List<SiteEntity> getAll(@Param("userId") int userId);

    //    https://stackoverflow.com/a/46013654/548473
    // @EntityGraph(attributePaths = {"siteEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM SiteEntity e  JOIN FETCH e.operator,  e.region, e.user  WHERE e.id=?1 and e.user.id=?2")
    SiteEntity getWithUser(int id, int userId);

}
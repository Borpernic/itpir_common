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
    int deleteAllByRegionId(int regionId);

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
/*    @Query("DELETE FROM SiteEntity e WHERE e.operator.id=:operatorId AND e.user.id=:userId")
    int deleteAllByOperatorAndUserId(@Param("operatorId") int id, @Param("userId") int userId);*/

    int deleteAllByOperatorIdAndUserId(int operatorId, int userId);

    @Modifying
    @Transactional
        //@Query("DELETE FROM SiteEntity e WHERE e.user.id=:userId")
    int deleteAllByRegionIdAndUserId(int regionId, int userId);

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


    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM SiteEntity e JOIN FETCH e.operator JOIN FETCH  e.region JOIN FETCH e.osEntities WHERE e.id=:id AND e.user.id=:userId ORDER BY e.operator.operator , e.number  ASC")
    Optional<SiteEntity> getWithOs(@Param("id") int id, @Param("userId") int userId);

    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM SiteEntity e JOIN FETCH e.operator JOIN FETCH  e.region JOIN FETCH e.osEntities WHERE e.id=:id ORDER BY e.operator.operator , e.number  ASC")
    Optional<SiteEntity> getWithOs(@Param("id") int id);

    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM SiteEntity e  JOIN FETCH e.operator JOIN FETCH  e.region ORDER BY e.operator.operator , e.number  ASC")
    List<SiteEntity> getAll();

    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM SiteEntity e JOIN FETCH e.operator JOIN FETCH  e.region where e.user.id=:userId ORDER BY e.operator.operator , e.number  ASC")
    List<SiteEntity> getAll(@Param("userId") int userId);

    @EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM SiteEntity e  JOIN FETCH e.operator JOIN FETCH  e.region left JOIN FETCH e.osEntities ORDER BY e.operator.operator , e.number  ASC")
    List<SiteEntity> getAllWithOs();

    @EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM SiteEntity e JOIN FETCH e.operator JOIN FETCH  e.region left JOIN FETCH e.osEntities where e.user.id=:userId ORDER BY e.operator.operator , e.number  ASC")
    List<SiteEntity> getAllWithOs(@Param("userId") int userId);

    //    https://stackoverflow.com/a/46013654/548473 e.osEntities,
    @EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM SiteEntity e JOIN FETCH e.operator JOIN FETCH e.region JOIN FETCH e.osEntities JOIN FETCH e.user  WHERE e.id=?1 and e.user.id=?2 ORDER BY e.operator.operator , e.number  ASC")
    SiteEntity getWithUser(int id, int userId);

}
package ru.lab729.itpir.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.ContactsAdEntity;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface ContactsAdRepository extends JpaRepository<ContactsAdEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ContactsAdEntity e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM ContactsAdEntity e WHERE e.id=:id AND e.user.id=:userId")
    int deleteByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    void deleteAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM ContactsAdEntity e ")
    int deleteAllEntity();

    @Modifying
    @Transactional
        //@Query("DELETE FROM ContactsAdEntity e WHERE e.user.id=:userId")
    int deleteAllBySiteId(int siteId);

    @Modifying
    @Transactional
        //@Query("DELETE FROM ContactsAdEntity e WHERE e.user.id=:userId")
    int deleteAllByEmail(String email);

    @Modifying
    @Transactional
        //@Query("DELETE FROM ContactsAdEntity e WHERE e.user.id=:userId")
    int deleteAllByConfirmed(boolean confirmed);

    @Modifying
    @Transactional
        //@Query("DELETE FROM ContactsAdEntity e WHERE e.user.id=:userId")
    int deleteAllByStatusId(int statusId);

    @Modifying
    @Transactional
        //@Query("DELETE FROM ContactsAdEntity e WHERE e.user.id=:userId")
    int deleteAllByCommentsContains(String comments);

    @Modifying
    @Transactional
    @Query("DELETE FROM ContactsAdEntity e WHERE e.user.id=:userId")
    int deleteAllByUserId(@Param("userId") int userId);


    @Modifying
    @Transactional
        //@Query("DELETE FROM ContactsAdEntity e WHERE e.user.id=:userId")
    int deleteAllByCommentsContainsAndUserId(String comments, int userId);

    @Override
    @Transactional
    ContactsAdEntity save(ContactsAdEntity item);

    /*
    @Transactional
    @Modifying
    OperatorEntity save(OperatorEntity operator, int userId);*/

    @Override
    Optional<ContactsAdEntity> findById(Integer id);


    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity e JOIN FETCH e.operator JOIN FETCH  e.region JOIN FETCH e.osEntities WHERE e.id=:id AND e.user.id=:userId ORDER BY e.operator.operator , e.number  ASC")
    Optional<ContactsAdEntity> getWithOs(@Param("id") int id, @Param("userId") int userId);

    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity e JOIN FETCH e.operator JOIN FETCH  e.region JOIN FETCH e.osEntities WHERE e.id=:id ORDER BY e.operator.operator , e.number  ASC")
    Optional<ContactsAdEntity> getWithOs(@Param("id") int id);

    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity e  JOIN FETCH e.operator JOIN FETCH  e.region ORDER BY e.operator.operator , e.number  ASC")
    List<ContactsAdEntity> getAll();

    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity e JOIN FETCH e.operator JOIN FETCH  e.region where e.user.id=:userId ORDER BY e.operator.operator , e.number  ASC")
    List<ContactsAdEntity> getAll(@Param("userId") int userId);

    @EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity e  JOIN FETCH e.operator JOIN FETCH  e.region left JOIN FETCH e.osEntities ORDER BY e.operator.operator , e.number  ASC")
    List<ContactsAdEntity> getAllWithOs();

    @EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity e JOIN FETCH e.operator JOIN FETCH  e.region left JOIN FETCH e.osEntities where e.user.id=:userId ORDER BY e.operator.operator , e.number  ASC")
    List<ContactsAdEntity> getAllWithOs(@Param("userId") int userId);

    //    https://stackoverflow.com/a/46013654/548473 e.osEntities,
    @EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity e JOIN FETCH e.operator JOIN FETCH e.region JOIN FETCH e.osEntities JOIN FETCH e.user  WHERE e.id=?1 and e.user.id=?2 ORDER BY e.operator.operator , e.number  ASC")
    ContactsAdEntity getWithUser(int id, int userId);

}
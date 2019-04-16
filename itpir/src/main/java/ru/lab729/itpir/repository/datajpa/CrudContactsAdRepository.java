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
public interface CrudContactsAdRepository extends JpaRepository<ContactsAdEntity, Integer> {

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
    @Query("DELETE FROM ContactsAdEntity e where e.user.id=:userId")
    int deleteAllEntity(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ContactsAdEntity e WHERE e.user.id=:userId")
    int deleteAllByUserId(@Param("userId") int userId);

    @Modifying
    @Transactional
        //@Query("DELETE FROM ContactsAdEntity e WHERE e.user.id=:userId")
    int deleteAllBySite_Id(int siteId);

    @Modifying
    @Transactional
        //@Query("DELETE FROM ContactsAdEntity e WHERE e.user.id=:userId")
    int deleteAllBySite_IdAndUserId(int siteId, int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ContactsAdEntity  e where (e.surname like %:text% or e.name like %:text% or e.middle_name like %:text% or e.position like %:text% or e.phone1 like %:text%" +
            "  or e.phone2  like  %:text% or e.email  like %:text%  or e.comments  like %:text% or e.city  like %:text%  or e.street  like %:text% or e.building like %:text% )")
    int deleteAllByText(@Param("text") String text);

    @Modifying
    @Transactional
    @Query("DELETE FROM ContactsAdEntity  e where (e.surname like %:text% or e.name like %:text% or e.middle_name like %:text% or e.position like %:text% or e.phone1 like %:text%" +
            "  or e.phone2  like  %:text% or e.email  like %:text%  or e.comments  like %:text% or e.city  like %:text%  or e.street  like %:text% or e.building like %:text% ) and e.user.id=:userId")
    int deleteAllByTextAndUserId(@Param("text") String text, @Param("userId") int userId);

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
    @Query("SELECT e FROM ContactsAdEntity e JOIN FETCH e.site JOIN FETCH  e.status WHERE e.id=:id AND e.user.id=:userId")
    Optional<ContactsAdEntity> get(@Param("id") int id, @Param("userId") int userId);

    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity e JOIN FETCH e.site JOIN FETCH  e.status WHERE e.id=:id")
    Optional<ContactsAdEntity> get(@Param("id") int id);

    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity e  JOIN FETCH e.site JOIN FETCH  e.status ORDER BY e.site.operator.operator, e.site.number, e.surname , e.name  ASC")
    List<ContactsAdEntity> getAll();

    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity e  JOIN FETCH e.site JOIN FETCH  e.status WHERE e.user.id=:userId ORDER BY e.site.operator.operator, e.site.number, e.surname , e.name  ASC")
    List<ContactsAdEntity> getAll(@Param("userId") int userId);

    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity  e where (e.surname like %:text% or e.name like %:text% or e.middle_name like %:text% or e.position like %:text% or e.phone1 like %:text% " +
            " or e.phone2  like  %:text% or e.email  like %:text%  or e.comments  like %:text% or e.city  like %:text%  or e.street  like %:text% or e.building like %:text% )  " +
            "ORDER BY e.site.operator.operator, e.site.number, e.surname , e.name  ASC")
    List<ContactsAdEntity> getAllByText(@Param("text") String text);

    //@EntityGraph(attributePaths = {"osEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity  e where (e.surname like %:text% or e.name like %:text% or e.middle_name like %:text% or e.position like %:text% or e.phone1 like %:text% " +
            " or e.phone2  like  %:text% or e.email  like %:text%  or e.comments  like %:text% or e.city  like %:text%  or e.street  like %:text% or e.building like %:text% ) and e.user.id=:userId " +
            "ORDER BY e.site.operator.operator, e.site.number, e.surname , e.name  ASC")
    List<ContactsAdEntity> getAllByText(@Param("text") String text, @Param("userId") int userId);

    //    https://stackoverflow.com/a/46013654/548473 e.osEntities,
    @EntityGraph(attributePaths = {"site"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM ContactsAdEntity e JOIN FETCH e.site JOIN FETCH  e.status JOIN FETCH e.user  JOIN FETCH e.site WHERE e.id=?1 and e.user.id=?2 " +
            "ORDER BY e.site.operator.operator, e.site.number, e.surname , e.name  ASC")
    ContactsAdEntity getWithUser(int id, int userId);

}
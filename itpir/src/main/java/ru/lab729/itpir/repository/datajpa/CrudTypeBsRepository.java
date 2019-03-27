package ru.lab729.itpir.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.TypeBsEntity;
import ru.lab729.itpir.model.TypeOsEntity;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudTypeBsRepository extends JpaRepository<TypeBsEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM TypeBsEntity e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM TypeBsEntity e WHERE e.id=:id AND e.user.id=:userId")
    int deleteByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    void deleteAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM TypeBsEntity e ")
    int deleteAllEntity();


    @Modifying
    @Transactional
    @Query("DELETE FROM TypeBsEntity e WHERE e.user.id=:userId")
    int deleteAllByUserId(@Param("userId") int userId);

    @Override
    @Transactional
    TypeBsEntity save(TypeBsEntity item);

    /*
    @Transactional
    @Modifying
    OperatorEntity save(OperatorEntity operator, int userId);*/

    @Override
    Optional<TypeBsEntity> findById(Integer id);

    @Query("SELECT e FROM TypeBsEntity e WHERE e.id=:id AND e.user.id=:userId")
    List<TypeBsEntity> get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT e FROM TypeBsEntity e  ORDER BY e.type  ASC")
    List<TypeBsEntity> getAll();

    @Query("SELECT e FROM TypeBsEntity e where e.user.id=:userId ORDER BY e.type  ASC")
    List<TypeBsEntity> getAll(@Param("userId") int userId);

    //    https://stackoverflow.com/a/46013654/548473
   // @EntityGraph(attributePaths = {"siteEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM TypeBsEntity e  JOIN FETCH e.user  WHERE e.id=?1 and e.user.id=?2")
    TypeBsEntity getWithUser(int id, int userId);


}
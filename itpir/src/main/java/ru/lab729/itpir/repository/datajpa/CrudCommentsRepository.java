package ru.lab729.itpir.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.CommentsEntity;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudCommentsRepository extends JpaRepository<CommentsEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM CommentsEntity e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM CommentsEntity e WHERE e.id=:id AND e.user.id=:userId")
    int deleteByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    void deleteAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM CommentsEntity e ")
    int deleteAllEntity();


    @Modifying
    @Transactional
    @Query("DELETE FROM CommentsEntity e WHERE e.user.id=:userId")
    int deleteAllByUserId(@Param("userId") int userId);

    @Override
    @Transactional
    CommentsEntity save(CommentsEntity item);

    /*
    @Transactional
    @Modifying
    OperatorEntity save(OperatorEntity operator, int userId);*/

    @Override
    Optional<CommentsEntity> findById(Integer id);

    @Query("SELECT e FROM CommentsEntity e WHERE e.id=:id AND e.user.id=:userId")
    List<CommentsEntity> get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT e FROM CommentsEntity e  ORDER BY e.id  ASC")
    List<CommentsEntity> getAll();

    @Query("SELECT e FROM CommentsEntity e where e.user.id=:userId ORDER BY e.id  ASC")
    List<CommentsEntity> getAll(@Param("userId") int userId);

    //    https://stackoverflow.com/a/46013654/548473
    // @EntityGraph(attributePaths = {"siteEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM CommentsEntity e  JOIN FETCH e.user  WHERE e.id=?1 and e.user.id=?2")
    CommentsEntity getWithUser(int id, int userId);


}
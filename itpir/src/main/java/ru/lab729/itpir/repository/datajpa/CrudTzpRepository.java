package ru.lab729.itpir.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.TzpEntity;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudTzpRepository extends JpaRepository<TzpEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM TzpEntity e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM TzpEntity e WHERE e.id=:id AND e.user.id=:userId")
    int deleteByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    void deleteAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM TzpEntity e ")
    int deleteAllEntity();

    @Modifying
    @Transactional
    @Query("DELETE FROM TzpEntity e WHERE e.user.id=:userId")
    int deleteAllByUserId(@Param("userId") int userId);

    @Override
    @Transactional
    TzpEntity save(TzpEntity item);

    @Override
    Optional<TzpEntity> findById(Integer id);

    @Query("SELECT e FROM TzpEntity e WHERE e.id=:id AND e.user.id=:userId")
    List<TzpEntity> get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT e FROM TzpEntity e  ORDER BY e.id  ASC")
    List<TzpEntity> getAll();

    @Query("SELECT e FROM TzpEntity e where e.user.id=:userId ORDER BY e.id  ASC")
    List<TzpEntity> getAll(@Param("userId") int userId);

    @Query("SELECT e FROM TzpEntity e  JOIN FETCH e.user  WHERE e.id=?1 and e.user.id=?2")
    TzpEntity getWithUser(int id, int userId);

}
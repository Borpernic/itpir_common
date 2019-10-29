package ru.lab729.itpir.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.ImplementerEntity;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudImplementerRepository extends JpaRepository<ImplementerEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ImplementerEntity e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM ImplementerEntity e WHERE e.id=:id AND e.user.id=:userId")
    int deleteByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    void deleteAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM ImplementerEntity e ")
    int deleteAllEntity();

    @Modifying
    @Transactional
    @Query("DELETE FROM ImplementerEntity e WHERE e.user.id=:userId")
    int deleteAllByUserId(@Param("userId") int userId);

    @Override
    @Transactional
    ImplementerEntity save(ImplementerEntity item);

    @Override
    Optional<ImplementerEntity> findById(Integer id);

    @Query("SELECT e FROM ImplementerEntity e WHERE e.id=:id AND e.user.id=:userId")
    List<ImplementerEntity> get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT e FROM ImplementerEntity e  ORDER BY e.implementer  ASC")
    List<ImplementerEntity> getAll();

    @Query("SELECT e FROM ImplementerEntity e where e.user.id=:userId ORDER BY e.implementer  ASC")
    List<ImplementerEntity> getAll(@Param("userId") int userId);

    @Query("SELECT e FROM ImplementerEntity e  JOIN FETCH e.user  WHERE e.id=?1 and e.user.id=?2")
    ImplementerEntity getWithUser(int id, int userId);

}
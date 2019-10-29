package ru.lab729.itpir.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.CustomerEntity;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudCustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM CustomerEntity e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM CustomerEntity e WHERE e.id=:id AND e.user.id=:userId")
    int deleteByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Modifying
    @Transactional
    void deleteAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM CustomerEntity e ")
    int deleteAllEntity();


    @Modifying
    @Transactional
    @Query("DELETE FROM CustomerEntity e WHERE e.user.id=:userId")
    int deleteAllByUserId(@Param("userId") int userId);

    @Override
    @Transactional
    CustomerEntity save(CustomerEntity item);

    /*
    @Transactional
    @Modifying
    OperatorEntity save(OperatorEntity operator, int userId);*/

    @Override
    Optional<CustomerEntity> findById(Integer id);

    @Query("SELECT e FROM CustomerEntity e WHERE e.id=:id AND e.user.id=:userId")
    List<CustomerEntity> get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT e FROM CustomerEntity e  ORDER BY e.customer  ASC")
    List<CustomerEntity> getAll();

    @Query("SELECT e FROM CustomerEntity e where e.user.id=:userId ORDER BY e.customer  ASC")
    List<CustomerEntity> getAll(@Param("userId") int userId);

    //    https://stackoverflow.com/a/46013654/548473
   // @EntityGraph(attributePaths = {"siteEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM CustomerEntity e  JOIN FETCH e.user  WHERE e.id=?1 and e.user.id=?2")
    CustomerEntity getWithUser(int id, int userId);


}
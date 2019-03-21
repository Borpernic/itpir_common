package ru.lab729.itpir.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.Meal;
import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.model.User;
import ru.lab729.itpir.repository.MealRepository;
import ru.lab729.itpir.repository.OperatorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaOperatorRepositoryImpl implements OperatorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public OperatorEntity save(OperatorEntity meal, int userId) {
        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }
        meal.setUser(em.getReference(User.class, userId));
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }


    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public boolean deleteAll(int userId) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public OperatorEntity get(int id) {
        return null;
    }

    @Override
    public OperatorEntity get(int id, int userId) {
        return null;
    }

    @Override
    public List<OperatorEntity> getAll(int userId) {
        return null;
    }

    @Override
    public List<OperatorEntity> getAll() {
        return null;
    }

    @Override
    public OperatorEntity getWithUser(int id, int userId) {
        return null;
    }
}
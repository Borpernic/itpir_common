package ru.lab729.itpir.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.StatusOsEntity;
import ru.lab729.itpir.model.User;
import ru.lab729.itpir.repository.StatusOsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaStatusOsRepositoryImpl implements StatusOsRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public StatusOsEntity save(StatusOsEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(em.getReference(User.class, userId));
        if (entity.isNew()) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
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
    public StatusOsEntity get(int id) {
        return null;
    }

    @Override
    public StatusOsEntity get(int id, int userId) {
        return null;
    }

    @Override
    public List<StatusOsEntity> getAll(int userId) {
        return null;
    }

    @Override
    public List<StatusOsEntity> getAll() {
        return null;
    }

    @Override
    public StatusOsEntity getWithUser(int id, int userId) {
        return null;
    }
}
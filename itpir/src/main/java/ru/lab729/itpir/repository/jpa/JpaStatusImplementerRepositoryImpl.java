package ru.lab729.itpir.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.StatusImplementerEntity;
import ru.lab729.itpir.model.User;
import ru.lab729.itpir.repository.StatusImplementerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaStatusImplementerRepositoryImpl implements StatusImplementerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public StatusImplementerEntity save(StatusImplementerEntity entity, int userId) {
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
    public StatusImplementerEntity get(int id) {
        return null;
    }

    @Override
    public StatusImplementerEntity get(int id, int userId) {
        return null;
    }

    @Override
    public List<StatusImplementerEntity> getAll(int userId) {
        return null;
    }

    @Override
    public List<StatusImplementerEntity> getAll() {
        return null;
    }

    @Override
    public StatusImplementerEntity getWithUser(int id, int userId) {
        return null;
    }
}
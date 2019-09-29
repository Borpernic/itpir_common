package ru.lab729.itpir.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.CuratorEntity;
import ru.lab729.itpir.model.User;
import ru.lab729.itpir.repository.CuratorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaCuratorRepositoryImpl implements CuratorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public CuratorEntity save(CuratorEntity entity, int userId) {
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
    public CuratorEntity get(int id) {
        return null;
    }

    @Override
    public CuratorEntity get(int id, int userId) {
        return null;
    }

    @Override
    public List<CuratorEntity> getAll(int userId) {
        return null;
    }

    @Override
    public List<CuratorEntity> getAll() {
        return null;
    }

    @Override
    public CuratorEntity getWithUser(int id, int userId) {
        return null;
    }
}
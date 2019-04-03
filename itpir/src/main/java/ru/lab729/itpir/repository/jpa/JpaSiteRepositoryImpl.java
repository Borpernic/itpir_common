package ru.lab729.itpir.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.SiteEntity;
import ru.lab729.itpir.model.User;
import ru.lab729.itpir.repository.SiteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaSiteRepositoryImpl implements SiteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public SiteEntity save(SiteEntity entity, int userId) {
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
    public boolean deleteAllByOperator(int operatorId, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllByOperator(int operatorId) {
        return false;
    }

    @Override
    public boolean deleteAllByRegion(int regionId, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllByRegion(int regionId) {
        return false;
    }

    @Override
    public boolean deleteAllByComments(String comments, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllByComments(String comments) {
        return false;
    }

    @Override
    public SiteEntity get(int id) {
        return null;
    }

    @Override
    public SiteEntity get(int id, int userId) {
        return null;
    }

    @Override
    public SiteEntity getWithOs(int id) {
        return null;
    }

    @Override
    public SiteEntity getWithOs(int id, int userId) {
        return null;
    }


    @Override
    public List<SiteEntity> getAll(int userId) {
        return null;
    }

    @Override
    public List<SiteEntity> getAll() {
        return null;
    }

    @Override
    public List<SiteEntity> getAllWithOs(int userId) {
        return null;
    }

    @Override
    public List<SiteEntity> getAllWithOs() {
        return null;
    }

    @Override
    public SiteEntity getWithUser(int id, int userId) {
        return null;
    }
}
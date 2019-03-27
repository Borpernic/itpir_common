package ru.lab729.itpir.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.AbstractBaseWithUserEntity;
import ru.lab729.itpir.model.TypeAmsEntity;
import ru.lab729.itpir.model.User;
import ru.lab729.itpir.repository.TypeAmsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaTypeAmsRepositoryImpl implements TypeAmsRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public TypeAmsEntity save(TypeAmsEntity entity, int userId) {

        return saveEntity(entity, userId, this.em);
    }

    private <T extends AbstractBaseWithUserEntity> T saveEntity(T entity, int userId, EntityManager em) {
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
    public TypeAmsEntity get(int id) {
        return null;
    }

    @Override
    public TypeAmsEntity get(int id, int userId) {
        return null;
    }

    @Override
    public List<TypeAmsEntity> getAll(int userId) {
        return null;
    }

    @Override
    public List<TypeAmsEntity> getAll() {
        return null;
    }

    @Override
    public TypeAmsEntity getWithUser(int id, int userId) {
        return null;
    }
}
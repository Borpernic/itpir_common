package ru.lab729.itpir.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.TypeImplementerEntity;
import ru.lab729.itpir.model.User;
import ru.lab729.itpir.repository.TypeImplementerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaTypeImplementerRepositoryImpl implements TypeImplementerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public TypeImplementerEntity save(TypeImplementerEntity entity, int userId) {
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
    public TypeImplementerEntity get(int id) {
        return null;
    }

    @Override
    public TypeImplementerEntity get(int id, int userId) {
        return null;
    }

    @Override
    public List<TypeImplementerEntity> getAll(int userId) {
        return null;
    }

    @Override
    public List<TypeImplementerEntity> getAll() {
        return null;
    }

    @Override
    public TypeImplementerEntity getWithUser(int id, int userId) {
        return null;
    }
}
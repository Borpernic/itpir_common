package ru.lab729.itpir.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.DepartmentEntity;
import ru.lab729.itpir.model.User;
import ru.lab729.itpir.repository.DepartmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaDepartmentRepositoryImpl implements DepartmentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public DepartmentEntity save(DepartmentEntity entity, int userId) {
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
    public DepartmentEntity get(int id) {
        return null;
    }

    @Override
    public DepartmentEntity get(int id, int userId) {
        return null;
    }

    @Override
    public List<DepartmentEntity> getAll(int userId) {
        return null;
    }

    @Override
    public List<DepartmentEntity> getAll() {
        return null;
    }

    @Override
    public DepartmentEntity getWithUser(int id, int userId) {
        return null;
    }
}
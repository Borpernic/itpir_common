package ru.lab729.itpir.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.ContactsAdEntity;
import ru.lab729.itpir.model.User;
import ru.lab729.itpir.repository.ContactsAdRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaContactsAdRepositoryImpl implements ContactsAdRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public ContactsAdEntity save(ContactsAdEntity entity, int userId) {
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
    public boolean deleteAllBySite(int siteId, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllBySite(int siteId) {
        return false;
    }

    @Override
    public boolean deleteAllByText(String text, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllByText(String text) {
        return false;
    }

    @Override
    public ContactsAdEntity get(int id) {
        return null;
    }

    @Override
    public ContactsAdEntity get(int id, int userId) {
        return null;
    }

    @Override
    public ContactsAdEntity getAllByText(String text) {
        return null;
    }

    @Override
    public ContactsAdEntity getAllByText(String text, int userId) {
        return null;
    }

    @Override
    public List<ContactsAdEntity> getAll(int userId) {
        return null;
    }

    @Override
    public List<ContactsAdEntity> getAll() {
        return null;
    }

}
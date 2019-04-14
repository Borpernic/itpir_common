package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.ContactsAdEntity;
import ru.lab729.itpir.repository.ContactsAdRepository;

import java.util.List;

@Repository
public class DataJpaContactsAdRepositoryImpl implements ContactsAdRepository {

    private final CrudContactsAdRepository crudContactsAdRepository;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaContactsAdRepositoryImpl(CrudContactsAdRepository crudContactsAdRepository, CrudUserRepository crudUserRepository) {
        this.crudContactsAdRepository = crudContactsAdRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public ContactsAdEntity save(ContactsAdEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(crudUserRepository.getOne(userId));
        return crudContactsAdRepository.save(entity);
    }


    @Override
    public boolean deleteAll(int userId) {

        return crudContactsAdRepository.deleteAllByUserId(userId) != 0;
    }

    @Override
    public boolean deleteAll() {

        return crudContactsAdRepository.deleteAllEntity() != 0;
    }

    @Override
    public boolean deleteAllBySite(int siteId, int userId) {
        return crudContactsAdRepository.deleteAllBySite_IdAndUserId(siteId, userId) != 0;
    }

    @Override
    public boolean deleteAllBySite(int siteId) {
        return crudContactsAdRepository.deleteAllBySite_Id(siteId) != 0;
    }

    @Override
    public boolean deleteAllByText(String text, int userId) {
        return crudContactsAdRepository.deleteAllByTextAndUserId(text, userId) != 0;
    }

    @Override
    public boolean deleteAllByText(String text) {
        return crudContactsAdRepository.deleteAllByText(text) != 0;
    }

    @Override
    public boolean delete(int id) {
        return crudContactsAdRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudContactsAdRepository.deleteByIdAndUserId(id, userId) != 0;
    }


    @Override
    public ContactsAdEntity getWithUser(int id, int userId) {
        return crudContactsAdRepository.getWithUser(id, userId);
    }


    @Override
    public ContactsAdEntity get(int id) {
        return crudContactsAdRepository.findById(id).orElse(null);
    }

    @Override
    public ContactsAdEntity get(int id, int userId) {
        return crudContactsAdRepository.getWithUser(id, userId);
    }

    @Override
    public List<ContactsAdEntity> getAllByText(String text) {
        return crudContactsAdRepository.getAllByText(text);
    }

    @Override
    public List<ContactsAdEntity> getAllByText(String text, int userId) {
        return crudContactsAdRepository.getAllByText(text, userId);
    }

    @Override
    public List<ContactsAdEntity> getAll(int userId) {

        return crudContactsAdRepository.getAll(userId);
    }

    @Override
    public List<ContactsAdEntity> getAll() {
        return crudContactsAdRepository.getAll();
    }


}

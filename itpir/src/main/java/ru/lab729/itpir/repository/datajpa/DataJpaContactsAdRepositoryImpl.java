package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.ContactsAdEntity;
import ru.lab729.itpir.repository.ContactsAdRepository;

import java.util.List;

@Repository
public class DataJpaContactsAdRepositoryImpl implements CrudContactsAdRepository {

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

        return crudContactsAdRepository.deleteAll()!= 0;
    }

    @Override
    public boolean deleteAllByOperator(int operatorId, int userId) {
        return crudContactsAdRepository.deleteAllByOperatorIdAndUserId(operatorId, userId) != 0;
    }

    @Override
    public boolean deleteAllByOperator(int operatorId) {
        return crudContactsAdRepository.deleteAllByOperator(operatorId)!=0;
    }

    @Override
    public boolean deleteAllByRegion(int regionId, int userId) {
        return crudContactsAdRepository.deleteAllByRegionIdAndUserId(regionId, userId) != 0;
    }

    @Override
    public boolean deleteAllByRegion(int regionId) {
        return crudContactsAdRepository.deleteAllByRegionId(regionId) != 0;
    }

    @Override
    public boolean deleteAllByComments(String comments, int userId) {
        return crudContactsAdRepository.deleteAllByCommentsContainsAndUserId(comments,userId)!=0;
    }

    @Override
    public boolean deleteAllByComments(String comments) {
        return crudContactsAdRepository.deleteAllByCommentsContains(comments)!=0;
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
    public ContactsAdEntity getWithOs(int id) {
        return crudContactsAdRepository.getWithOs(id).orElse(null);
    }

    @Override
    public ContactsAdEntity getWithOs(int id, int userId) {
        return crudContactsAdRepository.getWithOs(id,userId).orElse(null);
    }

    @Override
    public List<ContactsAdEntity> getAll(int userId) {

        return crudContactsAdRepository.getAll(userId);
    }

    @Override
    public List<ContactsAdEntity> getAll() {
        return crudContactsAdRepository.getAll();
    }

    @Override
    public List<ContactsAdEntity> getAllWithOs(int userId) {
        return crudContactsAdRepository.getAllWithOs(userId);
    }

    @Override
    public List<ContactsAdEntity> getAllWithOs() {
        return crudContactsAdRepository.getAllWithOs();
    }


}

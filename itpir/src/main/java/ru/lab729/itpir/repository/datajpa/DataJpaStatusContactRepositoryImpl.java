package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.RegionEntity;
import ru.lab729.itpir.model.StatusContactsEntity;
import ru.lab729.itpir.repository.RegionRepository;
import ru.lab729.itpir.repository.StatusContactRepository;

import java.util.List;

@Repository
public class DataJpaStatusContactRepositoryImpl implements StatusContactRepository {

    private final CrudStatusContactRepository  crudStatusContactRepository ;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaStatusContactRepositoryImpl(CrudStatusContactRepository crudStatusContactRepository, CrudUserRepository crudUserRepository) {
        this.crudStatusContactRepository = crudStatusContactRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public StatusContactsEntity save(StatusContactsEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(crudUserRepository.getOne(userId));
        return crudStatusContactRepository.save(entity);
    }



    @Override
    public boolean deleteAll(int userId) {

        return crudStatusContactRepository.deleteAllByUserId(userId) != 0;
    }

    @Override
    public boolean deleteAll() {

        return crudStatusContactRepository.deleteAllEntity() != 0;
    }

    @Override
    public boolean delete(int id) {
        return crudStatusContactRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudStatusContactRepository.deleteByIdAndUserId(id, userId) != 0;
    }


    @Override
    public StatusContactsEntity getWithUser(int id, int userId) {
        return crudStatusContactRepository.getWithUser(id, userId);
    }


    @Override
    public StatusContactsEntity get(int id) {
        return crudStatusContactRepository.findById(id).orElse(null);
    }

    @Override
    public StatusContactsEntity get(int id, int userId) {
        return crudStatusContactRepository.getWithUser(id, userId);
    }

    @Override
    public List<StatusContactsEntity> getAll(int userId) {

        return crudStatusContactRepository.getAll(userId);
    }

    @Override
    public List<StatusContactsEntity> getAll() {
        return crudStatusContactRepository.getAll();
    }


}

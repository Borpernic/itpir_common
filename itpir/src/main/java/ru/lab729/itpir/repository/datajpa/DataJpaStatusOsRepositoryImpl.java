package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.StatusContactsEntity;
import ru.lab729.itpir.model.StatusOsEntity;
import ru.lab729.itpir.repository.StatusContactRepository;
import ru.lab729.itpir.repository.StatusOsRepository;

import java.util.List;

@Repository
public class DataJpaStatusOsRepositoryImpl implements StatusOsRepository {

    private final CrudStatusOsRepository  crudEntityRepository ;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaStatusOsRepositoryImpl(CrudStatusOsRepository crudEntityRepository, CrudUserRepository crudUserRepository) {
        this.crudEntityRepository = crudEntityRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public StatusOsEntity save(StatusOsEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(crudUserRepository.getOne(userId));
        return crudEntityRepository.save(entity);
    }



    @Override
    public boolean deleteAll(int userId) {

        return crudEntityRepository.deleteAllByUserId(userId) != 0;
    }

    @Override
    public boolean deleteAll() {

        return crudEntityRepository.deleteAllEntity() != 0;
    }

    @Override
    public boolean delete(int id) {
        return crudEntityRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudEntityRepository.deleteByIdAndUserId(id, userId) != 0;
    }


    @Override
    public StatusOsEntity getWithUser(int id, int userId) {
        return crudEntityRepository.getWithUser(id, userId);
    }


    @Override
    public StatusOsEntity get(int id) {
        return crudEntityRepository.findById(id).orElse(null);
    }

    @Override
    public StatusOsEntity get(int id, int userId) {
        return crudEntityRepository.getWithUser(id, userId);
    }

    @Override
    public List<StatusOsEntity> getAll(int userId) {

        return crudEntityRepository.getAll(userId);
    }

    @Override
    public List<StatusOsEntity> getAll() {
        return crudEntityRepository.getAll();
    }


}

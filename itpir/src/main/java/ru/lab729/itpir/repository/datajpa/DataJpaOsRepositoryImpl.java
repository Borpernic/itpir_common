package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.OsEntity;
import ru.lab729.itpir.repository.OsRepository;

import java.util.List;

@Repository
public class DataJpaOsRepositoryImpl implements OsRepository {

    private final CrudOsRepository crudOsRepository;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaOsRepositoryImpl(CrudOsRepository crudOsRepository, CrudUserRepository crudUserRepository) {
        this.crudOsRepository = crudOsRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public OsEntity save(OsEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(crudUserRepository.getOne(userId));
        return crudOsRepository.save(entity);
    }


    @Override
    public boolean deleteAll(int userId) {

        return crudOsRepository.deleteAllByUserId(userId) != 0;
    }

    @Override
    public boolean deleteAll() {

        return crudOsRepository.deleteAllEntity() != 0;
    }

    @Override
    public boolean delete(int id) {
        return crudOsRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudOsRepository.deleteByIdAndUserId(id, userId) != 0;
    }


    @Override
    public OsEntity getWithUser(int id, int userId) {
        return crudOsRepository.getWithUser(id, userId);
    }


    @Override
    public OsEntity get(int id) {
        return crudOsRepository.findById(id).orElse(null);
    }

    @Override
    public OsEntity get(int id, int userId) {
        return crudOsRepository.getWithUser(id, userId);
    }

    @Override
    public List<OsEntity> getAll(int userId) {

        return crudOsRepository.getAll(userId);
    }

    @Override
    public List<OsEntity> getAll() {
        return crudOsRepository.getAll();
    }


}

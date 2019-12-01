package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.TzpEntity;
import ru.lab729.itpir.repository.TzpRepository;

import java.util.List;

@Repository
public class DataJpaTzpRepositoryImpl implements TzpRepository {

    private final CrudTzpRepository crudTzpRepository;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaTzpRepositoryImpl(CrudTzpRepository crudTzpRepository, CrudUserRepository crudUserRepository) {
        this.crudTzpRepository = crudTzpRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public TzpEntity save(TzpEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(crudUserRepository.getOne(userId));
        return crudTzpRepository.save(entity);
    }


    @Override
    public boolean deleteAll(int userId) {

        return crudTzpRepository.deleteAllByUserId(userId) != 0;
    }

    @Override
    public boolean deleteAll() {

        return crudTzpRepository.deleteAllEntity() != 0;
    }

    @Override
    public boolean delete(int id) {
        return crudTzpRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudTzpRepository.deleteByIdAndUserId(id, userId) != 0;
    }


    @Override
    public TzpEntity getWithUser(int id, int userId) {
        return crudTzpRepository.getWithUser(id, userId);
    }


    @Override
    public TzpEntity get(int id) {
        return crudTzpRepository.findById(id).orElse(null);
    }

    @Override
    public TzpEntity get(int id, int userId) {
        return crudTzpRepository.getWithUser(id, userId);
    }

    @Override
    public List<TzpEntity> getAll(int userId) {

        return crudTzpRepository.getAll(userId);
    }

    @Override
    public List<TzpEntity> getAll() {
        return crudTzpRepository.getAll();
    }


}

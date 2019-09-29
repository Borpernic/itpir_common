package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.CuratorEntity;
import ru.lab729.itpir.repository.CuratorRepository;

import java.util.List;

@Repository
public class DataJpaCuratorRepositoryImpl implements CuratorRepository {

    private final CrudCuratorRepository crudCuratorRepository;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaCuratorRepositoryImpl(CrudCuratorRepository crudCuratorRepository, CrudUserRepository crudUserRepository) {
        this.crudCuratorRepository = crudCuratorRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public CuratorEntity save(CuratorEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(crudUserRepository.getOne(userId));
        return crudCuratorRepository.save(entity);
    }


    @Override
    public boolean deleteAll(int userId) {

        return crudCuratorRepository.deleteAllByUserId(userId) != 0;
    }

    @Override
    public boolean deleteAll() {

        return crudCuratorRepository.deleteAllEntity() != 0;
    }

    @Override
    public boolean delete(int id) {
        return crudCuratorRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudCuratorRepository.deleteByIdAndUserId(id, userId) != 0;
    }


    @Override
    public CuratorEntity getWithUser(int id, int userId) {
        return crudCuratorRepository.getWithUser(id, userId);
    }


    @Override
    public CuratorEntity get(int id) {
        return crudCuratorRepository.findById(id).orElse(null);
    }

    @Override
    public CuratorEntity get(int id, int userId) {
        return crudCuratorRepository.getWithUser(id, userId);
    }

    @Override
    public List<CuratorEntity> getAll(int userId) {

        return crudCuratorRepository.getAll(userId);
    }

    @Override
    public List<CuratorEntity> getAll() {
        return crudCuratorRepository.getAll();
    }


}

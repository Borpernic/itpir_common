package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.InternalNumberEntity;
import ru.lab729.itpir.repository.InternalNumberRepository;

import java.util.List;

@Repository
public class DataJpaInternalNumberRepositoryImpl implements InternalNumberRepository {

    private final CrudInternalNumberRepository crudInternalNumberRepository;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaInternalNumberRepositoryImpl(CrudInternalNumberRepository crudInternalNumberRepository, CrudUserRepository crudUserRepository) {
        this.crudInternalNumberRepository = crudInternalNumberRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public InternalNumberEntity save(InternalNumberEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(crudUserRepository.getOne(userId));
        return crudInternalNumberRepository.save(entity);
    }


    @Override
    public boolean deleteAll(int userId) {

        return crudInternalNumberRepository.deleteAllByUserId(userId) != 0;
    }

    @Override
    public boolean deleteAll() {

        return crudInternalNumberRepository.deleteAllEntity() != 0;
    }

    @Override
    public boolean delete(int id) {
        return crudInternalNumberRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudInternalNumberRepository.deleteByIdAndUserId(id, userId) != 0;
    }


    @Override
    public InternalNumberEntity getWithUser(int id, int userId) {
        return crudInternalNumberRepository.getWithUser(id, userId);
    }


    @Override
    public InternalNumberEntity get(int id) {
        return crudInternalNumberRepository.findById(id).orElse(null);
    }

    @Override
    public InternalNumberEntity get(int id, int userId) {
        return crudInternalNumberRepository.getWithUser(id, userId);
    }

    @Override
    public List<InternalNumberEntity> getAll(int userId) {

        return crudInternalNumberRepository.getAll(userId);
    }

    @Override
    public List<InternalNumberEntity> getAll() {
        return crudInternalNumberRepository.getAll();
    }


}

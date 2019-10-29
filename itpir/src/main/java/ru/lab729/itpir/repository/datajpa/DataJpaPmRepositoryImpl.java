package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.PmEntity;
import ru.lab729.itpir.repository.PmRepository;

import java.util.List;

@Repository
public class DataJpaPmRepositoryImpl implements PmRepository {

    private final CrudPmRepository crudPmRepository;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaPmRepositoryImpl(CrudPmRepository crudPmRepository, CrudUserRepository crudUserRepository) {
        this.crudPmRepository = crudPmRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public PmEntity save(PmEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(crudUserRepository.getOne(userId));
        return crudPmRepository.save(entity);
    }



    @Override
    public boolean deleteAll(int userId) {

        return crudPmRepository.deleteAllByUserId(userId) != 0;
    }

    @Override
    public boolean deleteAll() {

        return crudPmRepository.deleteAllEntity() != 0;
    }

    @Override
    public boolean delete(int id) {
        return crudPmRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudPmRepository.deleteByIdAndUserId(id, userId) != 0;
    }


    @Override
    public PmEntity getWithUser(int id, int userId) {
        return crudPmRepository.getWithUser(id, userId);
    }


    @Override
    public PmEntity get(int id) {
        return crudPmRepository.findById(id).orElse(null);
    }

    @Override
    public PmEntity get(int id, int userId) {
        return crudPmRepository.getWithUser(id, userId);
    }

    @Override
    public List<PmEntity> getAll(int userId) {

        return crudPmRepository.getAll(userId);
    }

    @Override
    public List<PmEntity> getAll() {
        return crudPmRepository.getAll();
    }


}

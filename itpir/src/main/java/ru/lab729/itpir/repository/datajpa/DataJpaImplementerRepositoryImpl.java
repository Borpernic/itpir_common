package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.ImplementerEntity;
import ru.lab729.itpir.repository.ImplementerRepository;

import java.util.List;

@Repository
public class DataJpaImplementerRepositoryImpl implements ImplementerRepository {

    private final CrudImplementerRepository crudImplementerRepository;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaImplementerRepositoryImpl(CrudImplementerRepository crudImplementerRepository, CrudUserRepository crudUserRepository) {
        this.crudImplementerRepository = crudImplementerRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public ImplementerEntity save(ImplementerEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(crudUserRepository.getOne(userId));
        return crudImplementerRepository.save(entity);
    }


    @Override
    public boolean deleteAll(int userId) {

        return crudImplementerRepository.deleteAllByUserId(userId) != 0;
    }

    @Override
    public boolean deleteAll() {

        return crudImplementerRepository.deleteAllEntity() != 0;
    }

    @Override
    public boolean delete(int id) {
        return crudImplementerRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudImplementerRepository.deleteByIdAndUserId(id, userId) != 0;
    }


    @Override
    public ImplementerEntity getWithUser(int id, int userId) {
        return crudImplementerRepository.getWithUser(id, userId);
    }


    @Override
    public ImplementerEntity get(int id) {
        return crudImplementerRepository.findById(id).orElse(null);
    }

    @Override
    public ImplementerEntity get(int id, int userId) {
        return crudImplementerRepository.getWithUser(id, userId);
    }

    @Override
    public List<ImplementerEntity> getAll(int userId) {

        return crudImplementerRepository.getAll(userId);
    }

    @Override
    public List<ImplementerEntity> getAll() {
        return crudImplementerRepository.getAll();
    }


}

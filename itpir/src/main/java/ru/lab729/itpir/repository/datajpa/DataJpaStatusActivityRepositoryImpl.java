package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.StatusActivityEntity;
import ru.lab729.itpir.repository.StatusActivityRepository;

import java.util.List;

@Repository
public class DataJpaStatusActivityRepositoryImpl implements StatusActivityRepository {

    private final CrudStatusActivityRepository  crudEntityRepository ;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaStatusActivityRepositoryImpl(CrudStatusActivityRepository crudEntityRepository, CrudUserRepository crudUserRepository) {
        this.crudEntityRepository = crudEntityRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public StatusActivityEntity save(StatusActivityEntity entity, int userId) {
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
    public StatusActivityEntity getWithUser(int id, int userId) {
        return crudEntityRepository.getWithUser(id, userId);
    }


    @Override
    public StatusActivityEntity get(int id) {
        return crudEntityRepository.findById(id).orElse(null);
    }

    @Override
    public StatusActivityEntity get(int id, int userId) {
        return crudEntityRepository.getWithUser(id, userId);
    }

    @Override
    public List<StatusActivityEntity> getAll(int userId) {

        return crudEntityRepository.getAll(userId);
    }

    @Override
    public List<StatusActivityEntity> getAll() {
        return crudEntityRepository.getAll();
    }


}

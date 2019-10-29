package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.TypeBsEntity;
import ru.lab729.itpir.repository.TypeBsRepository;

import java.util.List;

@Repository
public class DataJpaTypeBsRepositoryImpl implements TypeBsRepository {

    private final CrudTypeBsRepository crudEntityRepository;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaTypeBsRepositoryImpl(CrudTypeBsRepository crudEntityRepository, CrudUserRepository crudUserRepository) {
        this.crudEntityRepository = crudEntityRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public TypeBsEntity save(TypeBsEntity entity, int userId) {
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
    public TypeBsEntity getWithUser(int id, int userId) {
        return crudEntityRepository.getWithUser(id, userId);
    }


    @Override
    public TypeBsEntity get(int id) {
        return crudEntityRepository.findById(id).orElse(null);
    }

    @Override
    public TypeBsEntity get(int id, int userId) {
        return crudEntityRepository.getWithUser(id, userId);
    }

    @Override
    public List<TypeBsEntity> getAll(int userId) {

        return crudEntityRepository.getAll(userId);
    }

    @Override
    public List<TypeBsEntity> getAll() {
        return crudEntityRepository.getAll();
    }


}

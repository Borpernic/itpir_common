package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.TypeAfsEntity;
import ru.lab729.itpir.repository.TypeAfsRepository;

import java.util.List;

@Repository
public class DataJpaTypeAfsRepositoryImpl implements TypeAfsRepository {

    private final CrudTypeAfsRepository crudEntityRepository;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaTypeAfsRepositoryImpl(CrudTypeAfsRepository crudEntityRepository, CrudUserRepository crudUserRepository) {
        this.crudEntityRepository = crudEntityRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public TypeAfsEntity save(TypeAfsEntity entity, int userId) {
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
    public TypeAfsEntity getWithUser(int id, int userId) {
        return crudEntityRepository.getWithUser(id, userId);
    }


    @Override
    public TypeAfsEntity get(int id) {
        return crudEntityRepository.findById(id).orElse(null);
    }

    @Override
    public TypeAfsEntity get(int id, int userId) {
        return crudEntityRepository.getWithUser(id, userId);
    }

    @Override
    public List<TypeAfsEntity> getAll(int userId) {

        return crudEntityRepository.getAll(userId);
    }

    @Override
    public List<TypeAfsEntity> getAll() {
        return crudEntityRepository.getAll();
    }


}

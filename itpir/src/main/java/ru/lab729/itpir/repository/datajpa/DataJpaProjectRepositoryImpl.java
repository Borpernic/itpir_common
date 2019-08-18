package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.ProjectEntity;
import ru.lab729.itpir.repository.ProjectRepository;

import java.util.List;

@Repository
public class DataJpaProjectRepositoryImpl implements ProjectRepository {

    private final CrudProjectRepository crudProjectRepository;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaProjectRepositoryImpl(CrudProjectRepository crudProjectRepository, CrudUserRepository crudUserRepository) {
        this.crudProjectRepository = crudProjectRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public ProjectEntity save(ProjectEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(crudUserRepository.getOne(userId));
        return crudProjectRepository.save(entity);
    }


    @Override
    public boolean deleteAll(int userId) {

        return crudProjectRepository.deleteAllByUserId(userId) != 0;
    }

    @Override
    public boolean deleteAll() {

        return crudProjectRepository.deleteAllEntity() != 0;
    }

    @Override
    public boolean delete(int id) {
        return crudProjectRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudProjectRepository.deleteByIdAndUserId(id, userId) != 0;
    }


    @Override
    public ProjectEntity getWithUser(int id, int userId) {
        return crudProjectRepository.getWithUser(id, userId);
    }


    @Override
    public ProjectEntity get(int id) {
        return crudProjectRepository.findById(id).orElse(null);
    }

    @Override
    public ProjectEntity get(int id, int userId) {
        return crudProjectRepository.getWithUser(id, userId);
    }

    @Override
    public List<ProjectEntity> getAll(int userId) {

        return crudProjectRepository.getAll(userId);
    }

    @Override
    public List<ProjectEntity> getAll() {
        return crudProjectRepository.getAll();
    }


}

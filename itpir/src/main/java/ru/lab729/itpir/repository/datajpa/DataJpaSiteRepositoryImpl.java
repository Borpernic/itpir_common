package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.SiteEntity;
import ru.lab729.itpir.repository.SiteRepository;

import java.util.List;

@Repository
public class DataJpaSiteRepositoryImpl implements SiteRepository {

    private final CrudSiteRepository crudSiteRepository;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaSiteRepositoryImpl(CrudSiteRepository crudSiteRepository, CrudUserRepository crudUserRepository) {
        this.crudSiteRepository = crudSiteRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public SiteEntity save(SiteEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(crudUserRepository.getOne(userId));
        return crudSiteRepository.save(entity);
    }


    @Override
    public boolean deleteAll(int userId) {

        return crudSiteRepository.deleteAllByUserId(userId) != 0;
    }

    @Override
    public boolean deleteAll() {

        return crudSiteRepository.deleteAllEntity() != 0;
    }

    @Override
    public boolean deleteAllByOperator(int operatorId, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllByOperator(int operatorId) {
        return false;
    }

    @Override
    public boolean deleteAllByRegion(int regionId, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllByRegion(int regionId) {
        return false;
    }

    @Override
    public boolean deleteAllByComments(String comments, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllByComments(String comments) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return crudSiteRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudSiteRepository.deleteByIdAndUserId(id, userId) != 0;
    }


    @Override
    public SiteEntity getWithUser(int id, int userId) {
        return crudSiteRepository.getWithUser(id, userId);
    }


    @Override
    public SiteEntity get(int id) {
        return crudSiteRepository.findById(id).orElse(null);
    }

    @Override
    public SiteEntity get(int id, int userId) {
        return crudSiteRepository.getWithUser(id, userId);
    }

    @Override
    public SiteEntity getWithOs(int id) {
        return null;
    }

    @Override
    public SiteEntity getWithOs(int id, int userId) {
        return null;
    }

    @Override
    public List<SiteEntity> getAll(int userId) {

        return crudSiteRepository.getAll(userId);
    }

    @Override
    public List<SiteEntity> getAll() {
        return crudSiteRepository.getAll();
    }

    @Override
    public List<SiteEntity> getAllWithOs(int userId) {
        return null;
    }

    @Override
    public List<SiteEntity> getAllWithOs() {
        return null;
    }


}

package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.RegionEntity;
import ru.lab729.itpir.repository.RegionRepository;

import java.util.List;

@Repository
public class DataJpaRegionRepositoryImpl implements RegionRepository {

    private final CrudRegionRepository  crudRegionRepository ;

    private final CrudUserRepository crudUserRepository;

    @Autowired
    public DataJpaRegionRepositoryImpl(CrudRegionRepository crudRegionRepository, CrudUserRepository crudUserRepository) {
        this.crudRegionRepository = crudRegionRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public RegionEntity save(RegionEntity entity, int userId) {
        if (!entity.isNew() && get(entity.getId(), userId) == null) {
            return null;
        }
        entity.setUser(crudUserRepository.getOne(userId));
        return crudRegionRepository.save(entity);
    }



    @Override
    public boolean deleteAll(int userId) {

        return crudRegionRepository.deleteAllByUserId(userId) != 0;
    }

    @Override
    public boolean deleteAll() {

        return crudRegionRepository.deleteAllEntity() != 0;
    }

    @Override
    public boolean delete(int id) {
        return crudRegionRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRegionRepository.deleteByIdAndUserId(id, userId) != 0;
    }


    @Override
    public RegionEntity getWithUser(int id, int userId) {
        return crudRegionRepository.getWithUser(id, userId);
    }


    @Override
    public RegionEntity get(int id) {
        return crudRegionRepository.findById(id).orElse(null);
    }

    @Override
    public RegionEntity get(int id, int userId) {
        return crudRegionRepository.getWithUser(id, userId);
    }

    @Override
    public List<RegionEntity> getAll(int userId) {

        return crudRegionRepository.getAll(userId);
    }

    @Override
    public List<RegionEntity> getAll() {
        return crudRegionRepository.getAll();
    }


}

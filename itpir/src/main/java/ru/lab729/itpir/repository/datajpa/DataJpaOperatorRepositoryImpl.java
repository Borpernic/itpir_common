package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.repository.OperatorRepository;

import java.util.List;

@Repository
public class DataJpaOperatorRepositoryImpl implements OperatorRepository {

    @Autowired
    private CrudOperatorRepository crudOperatorRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public OperatorEntity save(OperatorEntity operator, int userId) {
        if (!operator.isNew() && get(operator.getId(), userId) == null) {
            return null;
        }
        operator.setUser(crudUserRepository.getOne(userId));
        return crudOperatorRepository.save(operator);
    }

    @Override
    public OperatorEntity save(OperatorEntity operator) {

        if (!operator.isNew() && get(operator.getId()) == null) {
            return null;
        }
        operator.setUser(crudUserRepository.getOne(operator.getUser().getId()));
        return crudOperatorRepository.save(operator);
    }

    @Override
    public boolean delete(int id) {
        return crudOperatorRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudOperatorRepository.deleteByIdAndUserId(id, userId) != 0;
    }

    @Override
    public OperatorEntity get(int id) {
        return crudOperatorRepository.findById(id).orElse(null);
    }

    @Override
    public OperatorEntity get(int id, int userId) {
        return crudOperatorRepository.findById(id).filter(operatorEntity -> operatorEntity.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<OperatorEntity> getAll() {
        return crudOperatorRepository.getAll();
    }


    @Override
    public OperatorEntity getWithUser(int id, int userId) {
        return crudOperatorRepository.getWithUser(id, userId);
    }

    @Override
    public boolean deleteAll(int userId) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public List<OperatorEntity> getAll(int userId) {
        return null;
    }
}

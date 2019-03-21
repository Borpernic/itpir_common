package ru.lab729.itpir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.repository.OperatorRepository;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

import static ru.lab729.itpir.util.ValidationUtil.checkNotFoundWithId;

@Service
public class OperatorServiceImpl implements OperatorService {

    private final OperatorRepository repository;

    @Autowired
    public OperatorServiceImpl(OperatorRepository repository) {
        this.repository = repository;
    }


    @Override
    public OperatorEntity create(OperatorEntity operator, int userId) {
        Assert.notNull(operator, "operator must not be null");
        return repository.save(operator, userId);
    }


    @Override
    public OperatorEntity update(OperatorEntity operator) throws NotFoundException {
        Integer userId = get(operator.getId()).getUser().getId();
        return checkNotFoundWithId(repository.save(operator, userId), operator.getId(),"update without id");
    }

    @Override
    public OperatorEntity update(OperatorEntity operatorEntity, int userId) {
        return checkNotFoundWithId(repository.save(operatorEntity, userId), operatorEntity.getId());
    }

    @Override
    public OperatorEntity get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public OperatorEntity get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<OperatorEntity> getAll() {

        return repository.getAll();
    }

    @Override
    public List<OperatorEntity> getAll(int userId) {
        return repository.getAll(userId);
    }


    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public void deleteAll(int userId) throws NotFoundException {

        checkNotFoundWithId(repository.deleteAll(userId),0);
}

    @Override
    public void deleteAll() throws NotFoundException {
        checkNotFoundWithId(repository.deleteAll(),0);
    }



    @Override
    public OperatorEntity getWithUser(int id, int userId) {
        return checkNotFoundWithId(repository.getWithUser(id, userId), id);
    }
}
package ru.lab729.itpir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.lab729.itpir.model.Meal;
import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.repository.MealRepository;
import ru.lab729.itpir.repository.OperatorRepository;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.time.LocalDateTime;
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
    public OperatorEntity get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public OperatorEntity get(int id, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public List<OperatorEntity> getAll() {
        return null;
    }

    @Override
    public List<OperatorEntity> getAll(int userId) {
        return null;
    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {

    }

    @Override
    public void deleteAll(int id, int userId) throws NotFoundException {

    }

    @Override
    public OperatorEntity update(OperatorEntity operator) throws NotFoundException {
        return null;
    }

    @Override
    public OperatorEntity update(OperatorEntity operator, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public OperatorEntity create(OperatorEntity operator, int userId) {
        return null;
    }

    @Override
    public OperatorEntity getWithUser(int id, int userId) {
        return null;
    }
/*


    @Override
    public OperatorEntity get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public OperatorEntity get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<OperatorEntity> getAll() {
        return null;
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public void deleteAll(int id, int userId) throws NotFoundException {

    }

    @Override
    public OperatorEntity update(OperatorEntity operator) throws NotFoundException {
        return null;
    }

    @Override
    public List<OperatorEntity> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public OperatorEntity update(OperatorEntity operatorEntity, int userId) {
        return checkNotFoundWithId(repository.save(operatorEntity, userId), operatorEntity.getId());
    }

    @Override
    public OperatorEntity create(OperatorEntity operatorEntity, int userId) {
        Assert.notNull(operatorEntity, "meal must not be null");
        return repository.save(operatorEntity, userId);
    }

    @Override
    public OperatorEntity getWithUser(int id, int userId) {
        return checkNotFoundWithId(repository.getWithUser(id, userId), id);
    }*/
}
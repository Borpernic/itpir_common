package ru.lab729.itpir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.lab729.itpir.model.StatusActivityEntity;
import ru.lab729.itpir.repository.StatusActivityRepository;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

import static ru.lab729.itpir.util.ValidationUtil.checkNotFoundWithId;

@Service
public class StatusActivityServiceImpl implements StatusActivityService {

    private final StatusActivityRepository repository;

    @Autowired
    public StatusActivityServiceImpl(StatusActivityRepository repository) {
        this.repository = repository;
    }


    @Override
    public StatusActivityEntity create(StatusActivityEntity entity, int userId) {
        Assert.notNull(entity, "entity must not be null");
        return repository.save(entity, userId);
    }


    @Override
    public StatusActivityEntity update(StatusActivityEntity entity) throws NotFoundException {
        Integer userId = get(entity.getId()).getUser().getId();
        return checkNotFoundWithId(repository.save(entity, userId), entity.getId(), "update without id");
    }

    @Override
    public StatusActivityEntity update(StatusActivityEntity entity, int userId) {
        return checkNotFoundWithId(repository.save(entity, userId), entity.getId());
    }

    @Override
    public StatusActivityEntity get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public StatusActivityEntity get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<StatusActivityEntity> getAll() {

        return repository.getAll();
    }

    @Override
    public List<StatusActivityEntity> getAll(int userId) {
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

        checkNotFoundWithId(repository.deleteAll(userId), 0);
    }

    @Override
    public void deleteAll() throws NotFoundException {
        checkNotFoundWithId(repository.deleteAll(), 0);
    }


    @Override
    public StatusActivityEntity getWithUser(int id, int userId) {
        return checkNotFoundWithId(repository.getWithUser(id, userId), id);
    }
}
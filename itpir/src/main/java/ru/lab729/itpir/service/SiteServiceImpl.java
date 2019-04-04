package ru.lab729.itpir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.lab729.itpir.model.SiteEntity;
import ru.lab729.itpir.repository.SiteRepository;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

import static ru.lab729.itpir.util.ValidationUtil.checkNotFoundWithId;

@Service
public class SiteServiceImpl implements SiteService {

    private final SiteRepository repository;

    @Autowired
    public SiteServiceImpl(SiteRepository repository) {
        this.repository = repository;
    }

    @Override
    public SiteEntity create(SiteEntity entity, int userId) {
        Assert.notNull(entity, "operator must not be null");
        return repository.save(entity, userId);
    }

    @Override
    public SiteEntity update(SiteEntity entity) throws NotFoundException {
        Integer userId = get(entity.getId()).getUser().getId();
        return checkNotFoundWithId(repository.save(entity, userId), entity.getId(), "update without id");
    }

    @Override
    public SiteEntity update(SiteEntity entity, int userId) {
        return checkNotFoundWithId(repository.save(entity, userId), entity.getId());
    }

    @Override
    public SiteEntity get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public SiteEntity get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<SiteEntity> getAll() {

        return repository.getAll();
    }

    @Override
    public List<SiteEntity> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<SiteEntity> getAllWithOs(int userId) {
        return repository.getAllWithOs(userId);
    }

    @Override
    public List<SiteEntity> getAllWithOs() {
        return repository.getAllWithOs();
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
    public void deleteAllByOperator(int operatorId, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.deleteAllByOperator(operatorId,userId), 0);
    }

    @Override
    public void deleteAllByOperator(int operatorId) throws NotFoundException {
        checkNotFoundWithId(repository.deleteAllByOperator(operatorId), 0);
    }

    @Override
    public void deleteAllByRegion(int regionId, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.deleteAllByRegion(regionId,userId), 0);
    }

    @Override
    public void deleteAllByRegion(int regionId) throws NotFoundException {
        checkNotFoundWithId(repository.deleteAllByRegion(regionId), 0);
    }

    @Override
    public void deleteAllByComments(String comments, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.deleteAllByComments(comments,userId), 0);
    }

    @Override
    public void deleteAllByComments(String comments) throws NotFoundException {
        checkNotFoundWithId(repository.deleteAllByComments(comments), 0);
    }

    @Override
    public SiteEntity getWithUser(int id, int userId) {
        return checkNotFoundWithId(repository.getWithUser(id, userId), id);
    }

    @Override
    public SiteEntity getWithOs(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.getWithOs(id), id);
    }

    @Override
    public SiteEntity getWithOs(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.getWithOs(id, userId), id);
    }
}
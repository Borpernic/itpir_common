package ru.lab729.itpir.service;

import ru.lab729.itpir.model.ActivityEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface ActivityService {
    ActivityEntity get(int id) throws NotFoundException;

    ActivityEntity get(int id, int userId) throws NotFoundException;

    ActivityEntity getWithUser(int id, int userId);

    List<ActivityEntity> getAll();

    List<ActivityEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    ActivityEntity update(ActivityEntity entity) throws NotFoundException;

    ActivityEntity update(ActivityEntity entity, int userId) throws NotFoundException;

    ActivityEntity create(ActivityEntity entity, int userId);
}
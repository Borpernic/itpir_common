package ru.lab729.itpir.service;

import ru.lab729.itpir.model.TaskEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface TaskService {
    TaskEntity get(int id) throws NotFoundException;

    TaskEntity get(int id, int userId) throws NotFoundException;

    TaskEntity getWithUser(int id, int userId);

    List<TaskEntity> getAll();

    List<TaskEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    TaskEntity update(TaskEntity entity) throws NotFoundException;

    TaskEntity update(TaskEntity entity, int userId) throws NotFoundException;

    TaskEntity create(TaskEntity entity, int userId);
}
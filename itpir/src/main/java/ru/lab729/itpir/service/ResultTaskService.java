package ru.lab729.itpir.service;

import ru.lab729.itpir.model.ResultTaskEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface ResultTaskService {
    ResultTaskEntity get(int id) throws NotFoundException;

    ResultTaskEntity get(int id, int userId) throws NotFoundException;

    ResultTaskEntity getWithUser(int id, int userId);

    List<ResultTaskEntity> getAll();

    List<ResultTaskEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    ResultTaskEntity update(ResultTaskEntity entity) throws NotFoundException;

    ResultTaskEntity update(ResultTaskEntity entity, int userId) throws NotFoundException;

    ResultTaskEntity create(ResultTaskEntity entity, int userId);
}
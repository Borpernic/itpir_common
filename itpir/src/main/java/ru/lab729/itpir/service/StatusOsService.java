package ru.lab729.itpir.service;

import ru.lab729.itpir.model.StatusContactsEntity;
import ru.lab729.itpir.model.StatusOsEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface StatusOsService {
    StatusOsEntity get(int id) throws NotFoundException;

    StatusOsEntity get(int id, int userId) throws NotFoundException;

    StatusOsEntity getWithUser(int id, int userId);

    List<StatusOsEntity> getAll();

    List<StatusOsEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    StatusOsEntity update(StatusOsEntity entity) throws NotFoundException;

    StatusOsEntity update(StatusOsEntity entity, int userId) throws NotFoundException;

    StatusOsEntity create(StatusOsEntity entity, int userId);
}
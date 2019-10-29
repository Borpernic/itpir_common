package ru.lab729.itpir.service;

import ru.lab729.itpir.model.OsEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface OsService {
    OsEntity get(int id) throws NotFoundException;

    OsEntity get(int id, int userId) throws NotFoundException;

    OsEntity getWithUser(int id, int userId);

    List<OsEntity> getAll();

    List<OsEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    OsEntity update(OsEntity entity) throws NotFoundException;

    OsEntity update(OsEntity entity, int userId) throws NotFoundException;

    OsEntity create(OsEntity entity, int userId);
}
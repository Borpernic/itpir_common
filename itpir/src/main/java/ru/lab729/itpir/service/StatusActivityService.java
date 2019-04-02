package ru.lab729.itpir.service;

import ru.lab729.itpir.model.StatusActivityEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface StatusActivityService {
    StatusActivityEntity get(int id) throws NotFoundException;

    StatusActivityEntity get(int id, int userId) throws NotFoundException;

    StatusActivityEntity getWithUser(int id, int userId);

    List<StatusActivityEntity> getAll();

    List<StatusActivityEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    StatusActivityEntity update(StatusActivityEntity entity) throws NotFoundException;

    StatusActivityEntity update(StatusActivityEntity entity, int userId) throws NotFoundException;

    StatusActivityEntity create(StatusActivityEntity entity, int userId);
}
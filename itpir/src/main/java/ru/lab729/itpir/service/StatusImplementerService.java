package ru.lab729.itpir.service;

import ru.lab729.itpir.model.StatusImplementerEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface StatusImplementerService {
    StatusImplementerEntity get(int id) throws NotFoundException;

    StatusImplementerEntity get(int id, int userId) throws NotFoundException;

    StatusImplementerEntity getWithUser(int id, int userId);

    List<StatusImplementerEntity> getAll();

    List<StatusImplementerEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    StatusImplementerEntity update(StatusImplementerEntity entity) throws NotFoundException;

    StatusImplementerEntity update(StatusImplementerEntity entity, int userId) throws NotFoundException;

    StatusImplementerEntity create(StatusImplementerEntity entity, int userId);
}
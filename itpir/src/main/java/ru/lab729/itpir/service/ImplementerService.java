package ru.lab729.itpir.service;

import ru.lab729.itpir.model.ImplementerEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface ImplementerService {
    ImplementerEntity get(int id) throws NotFoundException;

    ImplementerEntity get(int id, int userId) throws NotFoundException;

    ImplementerEntity getWithUser(int id, int userId) throws NotFoundException;

    List<ImplementerEntity> getAll();

    List<ImplementerEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    ImplementerEntity update(ImplementerEntity entity) throws NotFoundException;

    ImplementerEntity update(ImplementerEntity entity, int userId) throws NotFoundException;

    ImplementerEntity create(ImplementerEntity entity, int userId);

}
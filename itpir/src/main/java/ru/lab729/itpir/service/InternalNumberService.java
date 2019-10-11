package ru.lab729.itpir.service;

import ru.lab729.itpir.model.InternalNumberEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface InternalNumberService {
    InternalNumberEntity get(int id) throws NotFoundException;

    InternalNumberEntity get(int id, int userId) throws NotFoundException;

    InternalNumberEntity getWithUser(int id, int userId);

    List<InternalNumberEntity> getAll();

    List<InternalNumberEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    InternalNumberEntity update(InternalNumberEntity entity) throws NotFoundException;

    InternalNumberEntity update(InternalNumberEntity entity, int userId) throws NotFoundException;

    InternalNumberEntity create(InternalNumberEntity entity, int userId);
}
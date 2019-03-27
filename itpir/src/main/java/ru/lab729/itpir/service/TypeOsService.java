package ru.lab729.itpir.service;

import ru.lab729.itpir.model.StatusOsEntity;
import ru.lab729.itpir.model.TypeOsEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface TypeOsService {
    TypeOsEntity get(int id) throws NotFoundException;

    TypeOsEntity get(int id, int userId) throws NotFoundException;

    TypeOsEntity getWithUser(int id, int userId);

    List<TypeOsEntity> getAll();

    List<TypeOsEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    TypeOsEntity update(TypeOsEntity entity) throws NotFoundException;

    TypeOsEntity update(TypeOsEntity entity, int userId) throws NotFoundException;

    TypeOsEntity create(TypeOsEntity entity, int userId);
}
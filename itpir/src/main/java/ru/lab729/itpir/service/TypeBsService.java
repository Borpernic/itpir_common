package ru.lab729.itpir.service;

import ru.lab729.itpir.model.TypeBsEntity;
import ru.lab729.itpir.model.TypeOsEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface TypeBsService {
    TypeBsEntity get(int id) throws NotFoundException;

    TypeBsEntity get(int id, int userId) throws NotFoundException;

    TypeBsEntity getWithUser(int id, int userId);

    List<TypeBsEntity> getAll();

    List<TypeBsEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    TypeBsEntity update(TypeBsEntity entity) throws NotFoundException;

    TypeBsEntity update(TypeBsEntity entity, int userId) throws NotFoundException;

    TypeBsEntity create(TypeBsEntity entity, int userId);
}
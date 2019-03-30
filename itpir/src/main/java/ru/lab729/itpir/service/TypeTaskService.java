package ru.lab729.itpir.service;

import ru.lab729.itpir.model.TypeTaskEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface TypeTaskService {
    TypeTaskEntity get(int id) throws NotFoundException;

    TypeTaskEntity get(int id, int userId) throws NotFoundException;

    TypeTaskEntity getWithUser(int id, int userId);

    List<TypeTaskEntity> getAll();

    List<TypeTaskEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    TypeTaskEntity update(TypeTaskEntity entity) throws NotFoundException;

    TypeTaskEntity update(TypeTaskEntity entity, int userId) throws NotFoundException;

    TypeTaskEntity create(TypeTaskEntity entity, int userId);
}
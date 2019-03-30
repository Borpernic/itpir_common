package ru.lab729.itpir.service;

import ru.lab729.itpir.model.TypeImplementerEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface TypeImplementerService {
    TypeImplementerEntity get(int id) throws NotFoundException;

    TypeImplementerEntity get(int id, int userId) throws NotFoundException;

    TypeImplementerEntity getWithUser(int id, int userId);

    List<TypeImplementerEntity> getAll();

    List<TypeImplementerEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    TypeImplementerEntity update(TypeImplementerEntity entity) throws NotFoundException;

    TypeImplementerEntity update(TypeImplementerEntity entity, int userId) throws NotFoundException;

    TypeImplementerEntity create(TypeImplementerEntity entity, int userId);
}
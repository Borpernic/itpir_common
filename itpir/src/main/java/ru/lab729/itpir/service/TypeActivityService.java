package ru.lab729.itpir.service;

import ru.lab729.itpir.model.TypeActivityEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface TypeActivityService {
    TypeActivityEntity get(int id) throws NotFoundException;

    TypeActivityEntity get(int id, int userId) throws NotFoundException;

    TypeActivityEntity getWithUser(int id, int userId);

    List<TypeActivityEntity> getAll();

    List<TypeActivityEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    TypeActivityEntity update(TypeActivityEntity entity) throws NotFoundException;

    TypeActivityEntity update(TypeActivityEntity entity, int userId) throws NotFoundException;

    TypeActivityEntity create(TypeActivityEntity entity, int userId);
}
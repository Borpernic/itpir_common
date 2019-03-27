package ru.lab729.itpir.service;

import ru.lab729.itpir.model.TypeAmsEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface TypeAmsService {
    TypeAmsEntity get(int id) throws NotFoundException;

    TypeAmsEntity get(int id, int userId) throws NotFoundException;

    TypeAmsEntity getWithUser(int id, int userId);

    List<TypeAmsEntity> getAll();

    List<TypeAmsEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    TypeAmsEntity update(TypeAmsEntity entity) throws NotFoundException;

    TypeAmsEntity update(TypeAmsEntity entity, int userId) throws NotFoundException;

    TypeAmsEntity create(TypeAmsEntity entity, int userId);
}
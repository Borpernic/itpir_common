package ru.lab729.itpir.service;

import ru.lab729.itpir.model.TypeAfsEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface TypeAfsService {
    TypeAfsEntity get(int id) throws NotFoundException;

    TypeAfsEntity get(int id, int userId) throws NotFoundException;

    TypeAfsEntity getWithUser(int id, int userId);

    List<TypeAfsEntity> getAll();

    List<TypeAfsEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    TypeAfsEntity update(TypeAfsEntity entity) throws NotFoundException;

    TypeAfsEntity update(TypeAfsEntity entity, int userId) throws NotFoundException;

    TypeAfsEntity create(TypeAfsEntity entity, int userId);
}
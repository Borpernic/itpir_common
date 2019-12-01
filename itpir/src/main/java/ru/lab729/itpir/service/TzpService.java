package ru.lab729.itpir.service;

import ru.lab729.itpir.model.TzpEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface TzpService {
    TzpEntity get(int id) throws NotFoundException;

    TzpEntity get(int id, int userId) throws NotFoundException;

    TzpEntity getWithUser(int id, int userId) throws NotFoundException;

    List<TzpEntity> getAll();

    List<TzpEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    TzpEntity update(TzpEntity entity) throws NotFoundException;

    TzpEntity update(TzpEntity entity, int userId) throws NotFoundException;

    TzpEntity create(TzpEntity entity, int userId);

}
package ru.lab729.itpir.service;

import ru.lab729.itpir.model.CuratorEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface CuratorService {
    CuratorEntity get(int id) throws NotFoundException;

    CuratorEntity get(int id, int userId) throws NotFoundException;

    CuratorEntity getWithUser(int id, int userId);

    List<CuratorEntity> getAll();

    List<CuratorEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    CuratorEntity update(CuratorEntity entity) throws NotFoundException;

    CuratorEntity update(CuratorEntity entity, int userId) throws NotFoundException;

    CuratorEntity create(CuratorEntity entity, int userId);
}
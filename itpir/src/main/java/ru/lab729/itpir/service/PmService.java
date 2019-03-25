package ru.lab729.itpir.service;

import ru.lab729.itpir.model.PmEntity;
import ru.lab729.itpir.model.StatusContactsEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface PmService {
    PmEntity get(int id) throws NotFoundException;

    PmEntity get(int id, int userId) throws NotFoundException;

    PmEntity getWithUser(int id, int userId);

    List<PmEntity> getAll();

    List<PmEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    PmEntity update(PmEntity entity) throws NotFoundException;

    PmEntity update(PmEntity entity, int userId) throws NotFoundException;

    PmEntity create(PmEntity entity, int userId);
}
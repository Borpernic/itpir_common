package ru.lab729.itpir.service;

import ru.lab729.itpir.model.StatusContactsEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface StatusContactService {
    StatusContactsEntity get(int id) throws NotFoundException;

    StatusContactsEntity get(int id, int userId) throws NotFoundException;

    StatusContactsEntity getWithUser(int id, int userId);

    List<StatusContactsEntity> getAll();

    List<StatusContactsEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    StatusContactsEntity update(StatusContactsEntity entity) throws NotFoundException;

    StatusContactsEntity update(StatusContactsEntity entity, int userId) throws NotFoundException;

    StatusContactsEntity create(StatusContactsEntity entity, int userId);
}
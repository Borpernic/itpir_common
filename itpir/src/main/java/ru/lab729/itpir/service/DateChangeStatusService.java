package ru.lab729.itpir.service;

import ru.lab729.itpir.model.DateChangeStatusEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface DateChangeStatusService {
    DateChangeStatusEntity get(int id) throws NotFoundException;

    DateChangeStatusEntity get(int id, int userId) throws NotFoundException;

    DateChangeStatusEntity getWithUser(int id, int userId);

    List<DateChangeStatusEntity> getAll();

    List<DateChangeStatusEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    DateChangeStatusEntity update(DateChangeStatusEntity entity) throws NotFoundException;

    DateChangeStatusEntity update(DateChangeStatusEntity entity, int userId) throws NotFoundException;

    DateChangeStatusEntity create(DateChangeStatusEntity entity, int userId);
}
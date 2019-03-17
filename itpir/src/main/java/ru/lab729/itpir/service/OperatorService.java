package ru.lab729.itpir.service;

import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface OperatorService {
    OperatorEntity get(int id) throws NotFoundException;

    OperatorEntity get(int id, int userId) throws NotFoundException;

    List<OperatorEntity> getAll();

    List<OperatorEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll( int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    OperatorEntity update(OperatorEntity operator) throws NotFoundException;

    OperatorEntity update(OperatorEntity operator, int userId) throws NotFoundException;

    OperatorEntity create(OperatorEntity operator, int userId);

    OperatorEntity getWithUser(int id, int userId);
}
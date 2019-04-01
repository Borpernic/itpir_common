package ru.lab729.itpir.service;

import ru.lab729.itpir.model.DepartmentEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface DepartmentService {
    DepartmentEntity get(int id) throws NotFoundException;

    DepartmentEntity get(int id, int userId) throws NotFoundException;

    DepartmentEntity getWithUser(int id, int userId);

    List<DepartmentEntity> getAll();

    List<DepartmentEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    DepartmentEntity update(DepartmentEntity entity) throws NotFoundException;

    DepartmentEntity update(DepartmentEntity entity, int userId) throws NotFoundException;

    DepartmentEntity create(DepartmentEntity entity, int userId);
}
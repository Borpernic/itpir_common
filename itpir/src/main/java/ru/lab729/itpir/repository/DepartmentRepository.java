package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.DepartmentEntity;

import java.util.List;

public interface DepartmentRepository {

    // null if updated operator do not belong to userId
    DepartmentEntity save(DepartmentEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    DepartmentEntity get(int id);

    // null if operator do not belong to userId
    DepartmentEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<DepartmentEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<DepartmentEntity> getAll();

    default DepartmentEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

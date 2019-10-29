package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.StatusOsEntity;

import java.util.List;

public interface StatusOsRepository {

    // null if updated operator do not belong to userId
    StatusOsEntity save(StatusOsEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    StatusOsEntity get(int id);

    // null if operator do not belong to userId
    StatusOsEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<StatusOsEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<StatusOsEntity> getAll();

    default StatusOsEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

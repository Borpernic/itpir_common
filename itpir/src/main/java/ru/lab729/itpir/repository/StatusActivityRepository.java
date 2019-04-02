package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.StatusActivityEntity;

import java.util.List;

public interface StatusActivityRepository {

    // null if updated operator do not belong to userId
    StatusActivityEntity save(StatusActivityEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    StatusActivityEntity get(int id);

    // null if operator do not belong to userId
    StatusActivityEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<StatusActivityEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<StatusActivityEntity> getAll();

    default StatusActivityEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

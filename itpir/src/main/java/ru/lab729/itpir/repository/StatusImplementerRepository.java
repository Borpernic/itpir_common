package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.StatusImplementerEntity;

import java.util.List;

public interface StatusImplementerRepository {

    // null if updated operator do not belong to userId
    StatusImplementerEntity save(StatusImplementerEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    StatusImplementerEntity get(int id);

    // null if operator do not belong to userId
    StatusImplementerEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<StatusImplementerEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<StatusImplementerEntity> getAll();

    default StatusImplementerEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

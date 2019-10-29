package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.OsEntity;

import java.util.List;

public interface OsRepository {

    // null if updated operator do not belong to userId
    OsEntity save(OsEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    OsEntity get(int id);

    // null if operator do not belong to userId
    OsEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<OsEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<OsEntity> getAll();

    default OsEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

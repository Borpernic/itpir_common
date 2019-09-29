package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.CuratorEntity;

import java.util.List;

public interface CuratorRepository {

    // null if updated operator do not belong to userId
    CuratorEntity save(CuratorEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    CuratorEntity get(int id);

    // null if operator do not belong to userId
    CuratorEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<CuratorEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<CuratorEntity> getAll();

    default CuratorEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

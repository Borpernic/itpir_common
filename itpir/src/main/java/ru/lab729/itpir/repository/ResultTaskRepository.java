package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.ResultTaskEntity;

import java.util.List;

public interface ResultTaskRepository {

    // null if updated operator do not belong to userId
    ResultTaskEntity save(ResultTaskEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    ResultTaskEntity get(int id);

    // null if operator do not belong to userId
    ResultTaskEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<ResultTaskEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<ResultTaskEntity> getAll();

    default ResultTaskEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

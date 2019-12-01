package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.TzpEntity;

import java.util.List;

public interface TzpRepository {

    // null if updated operator do not belong to userId
    TzpEntity save(TzpEntity operator, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    TzpEntity get(int id);

    // null if operator do not belong to userId
    TzpEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<TzpEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<TzpEntity> getAll();

    default TzpEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

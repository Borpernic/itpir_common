package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.OperatorEntity;

import java.util.List;

public interface OperatorRepository {

    // null if updated operator do not belong to userId
    OperatorEntity save(OperatorEntity operator, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    OperatorEntity get(int id);

    // null if operator do not belong to userId
    OperatorEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<OperatorEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<OperatorEntity> getAll();

    default OperatorEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

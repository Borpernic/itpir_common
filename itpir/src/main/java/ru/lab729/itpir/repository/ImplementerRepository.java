package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.ImplementerEntity;

import java.util.List;

public interface ImplementerRepository {

    // null if updated operator do not belong to userId
    ImplementerEntity save(ImplementerEntity operator, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    ImplementerEntity get(int id);

    // null if operator do not belong to userId
    ImplementerEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<ImplementerEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<ImplementerEntity> getAll();

    default ImplementerEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

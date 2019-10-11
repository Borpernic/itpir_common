package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.InternalNumberEntity;

import java.util.List;

public interface InternalNumberRepository {

    // null if updated operator do not belong to userId
    InternalNumberEntity save(InternalNumberEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    InternalNumberEntity get(int id);

    // null if operator do not belong to userId
    InternalNumberEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<InternalNumberEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<InternalNumberEntity> getAll();

    default InternalNumberEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

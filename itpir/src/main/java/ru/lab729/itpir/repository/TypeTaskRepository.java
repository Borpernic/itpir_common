package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.TypeTaskEntity;

import java.util.List;

public interface TypeTaskRepository {

    // null if updated operator do not belong to userId
    TypeTaskEntity save(TypeTaskEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    TypeTaskEntity get(int id);

    // null if operator do not belong to userId
    TypeTaskEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<TypeTaskEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<TypeTaskEntity> getAll();

    default TypeTaskEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.TypeActivityEntity;

import java.util.List;

public interface TypeActivityRepository {

    // null if updated operator do not belong to userId
    TypeActivityEntity save(TypeActivityEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    TypeActivityEntity get(int id);

    // null if operator do not belong to userId
    TypeActivityEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<TypeActivityEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<TypeActivityEntity> getAll();

    default TypeActivityEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.TypeAmsEntity;

import java.util.List;

public interface TypeAmsRepository {

    // null if updated operator do not belong to userId
    TypeAmsEntity save(TypeAmsEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    TypeAmsEntity get(int id);

    // null if operator do not belong to userId
    TypeAmsEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<TypeAmsEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<TypeAmsEntity> getAll();

    default TypeAmsEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

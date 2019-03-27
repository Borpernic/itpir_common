package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.StatusOsEntity;
import ru.lab729.itpir.model.TypeOsEntity;

import java.util.List;

public interface TypeOsRepository {

    // null if updated operator do not belong to userId
    TypeOsEntity save(TypeOsEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    TypeOsEntity get(int id);

    // null if operator do not belong to userId
    TypeOsEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<TypeOsEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<TypeOsEntity> getAll();

    default TypeOsEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

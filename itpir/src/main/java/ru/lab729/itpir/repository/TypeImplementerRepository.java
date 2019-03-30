package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.TypeImplementerEntity;

import java.util.List;

public interface TypeImplementerRepository {

    // null if updated operator do not belong to userId
    TypeImplementerEntity save(TypeImplementerEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    TypeImplementerEntity get(int id);

    // null if operator do not belong to userId
    TypeImplementerEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<TypeImplementerEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<TypeImplementerEntity> getAll();

    default TypeImplementerEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

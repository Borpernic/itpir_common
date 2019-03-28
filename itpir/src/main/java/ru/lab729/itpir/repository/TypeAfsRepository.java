package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.TypeAfsEntity;

import java.util.List;

public interface TypeAfsRepository {

    // null if updated operator do not belong to userId
    TypeAfsEntity save(TypeAfsEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    TypeAfsEntity get(int id);

    // null if operator do not belong to userId
    TypeAfsEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<TypeAfsEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<TypeAfsEntity> getAll();

    default TypeAfsEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

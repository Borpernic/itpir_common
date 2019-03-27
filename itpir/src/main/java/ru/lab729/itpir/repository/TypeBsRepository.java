package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.TypeBsEntity;
import ru.lab729.itpir.model.TypeOsEntity;

import java.util.List;

public interface TypeBsRepository {

    // null if updated operator do not belong to userId
    TypeBsEntity save(TypeBsEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    TypeBsEntity get(int id);

    // null if operator do not belong to userId
    TypeBsEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<TypeBsEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<TypeBsEntity> getAll();

    default TypeBsEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

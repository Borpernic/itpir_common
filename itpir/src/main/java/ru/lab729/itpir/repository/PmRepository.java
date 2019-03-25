package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.PmEntity;
import ru.lab729.itpir.model.StatusContactsEntity;

import java.util.List;

public interface PmRepository {

    // null if updated operator do not belong to userId
    PmEntity save(PmEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    PmEntity get(int id);

    // null if operator do not belong to userId
    PmEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<PmEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<PmEntity> getAll();

    default PmEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

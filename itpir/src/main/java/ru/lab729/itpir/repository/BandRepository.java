package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.BandEntity;

import java.util.List;

public interface BandRepository {

    // null if updated operator do not belong to userId
    BandEntity save(BandEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    BandEntity get(int id);

    // null if operator do not belong to userId
    BandEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<BandEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<BandEntity> getAll();

    default BandEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

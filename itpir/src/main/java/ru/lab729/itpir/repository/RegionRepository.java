package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.model.RegionEntity;

import java.util.List;

public interface RegionRepository {

    // null if updated operator do not belong to userId
    RegionEntity save(RegionEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    RegionEntity get(int id);

    // null if operator do not belong to userId
    RegionEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<RegionEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<RegionEntity> getAll();

    default RegionEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.ActivityEntity;

import java.util.List;

public interface ActivityRepository {

    // null if updated operator do not belong to userId
    ActivityEntity save(ActivityEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    ActivityEntity get(int id);

    // null if operator do not belong to userId
    ActivityEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<ActivityEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<ActivityEntity> getAll();

    default ActivityEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

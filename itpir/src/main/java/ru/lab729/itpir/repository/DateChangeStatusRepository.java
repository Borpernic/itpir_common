package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.DateChangeStatusEntity;

import java.util.List;

public interface DateChangeStatusRepository {

    // null if updated operator do not belong to userId
    DateChangeStatusEntity save(DateChangeStatusEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    DateChangeStatusEntity get(int id);

    // null if operator do not belong to userId
    DateChangeStatusEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<DateChangeStatusEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<DateChangeStatusEntity> getAll();

    default DateChangeStatusEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

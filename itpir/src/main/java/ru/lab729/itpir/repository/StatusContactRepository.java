package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.StatusContactsEntity;

import java.util.List;

public interface StatusContactRepository {

    // null if updated operator do not belong to userId
    StatusContactsEntity save(StatusContactsEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    StatusContactsEntity get(int id);

    // null if operator do not belong to userId
    StatusContactsEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<StatusContactsEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<StatusContactsEntity> getAll();

    default StatusContactsEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

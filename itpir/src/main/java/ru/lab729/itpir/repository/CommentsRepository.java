package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.CommentsEntity;

import java.util.List;

public interface CommentsRepository {

    // null if updated operator do not belong to userId
    CommentsEntity save(CommentsEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    CommentsEntity get(int id);

    // null if operator do not belong to userId
    CommentsEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<CommentsEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<CommentsEntity> getAll();

    default CommentsEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.TaskEntity;

import java.util.List;

public interface TaskRepository {

    // null if updated operator do not belong to userId
    TaskEntity save(TaskEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    TaskEntity get(int id);

    // null if operator do not belong to userId
    TaskEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<TaskEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<TaskEntity> getAll();

    default TaskEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.ProjectEntity;

import java.util.List;

public interface ProjectRepository {

    // null if updated operator do not belong to userId
    ProjectEntity save(ProjectEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    ProjectEntity get(int id);

    // null if operator do not belong to userId
    ProjectEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<ProjectEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<ProjectEntity> getAll();

    default ProjectEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

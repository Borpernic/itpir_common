package ru.lab729.itpir.service;

import ru.lab729.itpir.model.ProjectEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface ProjectService {
    ProjectEntity get(int id) throws NotFoundException;

    ProjectEntity get(int id, int userId) throws NotFoundException;

    ProjectEntity getWithUser(int id, int userId);

    List<ProjectEntity> getAll();

    List<ProjectEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    ProjectEntity update(ProjectEntity entity) throws NotFoundException;

    ProjectEntity update(ProjectEntity entity, int userId) throws NotFoundException;

    ProjectEntity create(ProjectEntity entity, int userId);
}
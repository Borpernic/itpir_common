package ru.lab729.itpir.service;

import ru.lab729.itpir.model.CommentsEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface CommentsService {
    CommentsEntity get(int id) throws NotFoundException;

    CommentsEntity get(int id, int userId) throws NotFoundException;

    CommentsEntity getWithUser(int id, int userId);

    List<CommentsEntity> getAll();

    List<CommentsEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    CommentsEntity update(CommentsEntity entity) throws NotFoundException;

    CommentsEntity update(CommentsEntity entity, int userId) throws NotFoundException;

    CommentsEntity create(CommentsEntity entity, int userId);
}
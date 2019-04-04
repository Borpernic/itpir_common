package ru.lab729.itpir.service;

import ru.lab729.itpir.model.SiteEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface SiteService {
    SiteEntity get(int id) throws NotFoundException;

    SiteEntity get(int id, int userId) throws NotFoundException;

    SiteEntity getWithUser(int id, int userId) throws NotFoundException;

    SiteEntity getWithOs(int id) throws NotFoundException;

    SiteEntity getWithOs(int id, int userId) throws NotFoundException;

    List<SiteEntity> getAll();

    List<SiteEntity> getAll(int userId);

    List<SiteEntity> getAllWithOs(int userId);

    List<SiteEntity> getAllWithOs();

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    void deleteAllByOperator(int operatorId, int userId) throws NotFoundException;

    void deleteAllByOperator(int operatorId) throws NotFoundException;

    void deleteAllByRegion(int regionId, int userId) throws NotFoundException;

    void deleteAllByRegion(int regionId) throws NotFoundException;

    void deleteAllByComments(String comments, int userId) throws NotFoundException;

    void deleteAllByComments(String comments) throws NotFoundException;

    SiteEntity update(SiteEntity entity) throws NotFoundException;

    SiteEntity update(SiteEntity entity, int userId) throws NotFoundException;

    SiteEntity create(SiteEntity entity, int userId);

}
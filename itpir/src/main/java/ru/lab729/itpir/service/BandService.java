package ru.lab729.itpir.service;

import ru.lab729.itpir.model.BandEntity;
import ru.lab729.itpir.model.CustomerEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface BandService {
    BandEntity get(int id) throws NotFoundException;

    BandEntity get(int id, int userId) throws NotFoundException;

    BandEntity getWithUser(int id, int userId);

    List<BandEntity> getAll();

    List<BandEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    BandEntity update(BandEntity entity) throws NotFoundException;

    BandEntity update(BandEntity entity, int userId) throws NotFoundException;

    BandEntity create(BandEntity entity, int userId);
}
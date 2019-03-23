package ru.lab729.itpir.service;

import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.model.RegionEntity;
import ru.lab729.itpir.repository.RegionRepository;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface RegionService {
    RegionEntity get(int id) throws NotFoundException;

    RegionEntity get(int id, int userId) throws NotFoundException;

    RegionEntity getWithUser(int id, int userId);

    List<RegionEntity> getAll();

    List<RegionEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    RegionEntity update(RegionEntity entity) throws NotFoundException;

    RegionEntity update(RegionEntity entity, int userId) throws NotFoundException;

    RegionEntity create(RegionEntity entity, int userId);
}
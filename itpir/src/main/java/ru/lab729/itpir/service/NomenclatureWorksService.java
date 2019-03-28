package ru.lab729.itpir.service;

import ru.lab729.itpir.model.NomenclatureWorksEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface NomenclatureWorksService {
    NomenclatureWorksEntity get(int id) throws NotFoundException;

    NomenclatureWorksEntity get(int id, int userId) throws NotFoundException;

    NomenclatureWorksEntity getWithUser(int id, int userId);

    List<NomenclatureWorksEntity> getAll();

    List<NomenclatureWorksEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    NomenclatureWorksEntity update(NomenclatureWorksEntity entity) throws NotFoundException;

    NomenclatureWorksEntity update(NomenclatureWorksEntity entity, int userId) throws NotFoundException;

    NomenclatureWorksEntity create(NomenclatureWorksEntity entity, int userId);
}
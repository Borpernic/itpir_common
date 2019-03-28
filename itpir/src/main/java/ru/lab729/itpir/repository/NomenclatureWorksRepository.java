package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.NomenclatureWorksEntity;

import java.util.List;

public interface NomenclatureWorksRepository {

    // null if updated operator do not belong to userId
    NomenclatureWorksEntity save(NomenclatureWorksEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    NomenclatureWorksEntity get(int id);

    // null if operator do not belong to userId
    NomenclatureWorksEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<NomenclatureWorksEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<NomenclatureWorksEntity> getAll();

    default NomenclatureWorksEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

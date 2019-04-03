package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.SiteEntity;

import java.util.List;

public interface SiteRepository {

    // null if updated operator do not belong to userId
    SiteEntity save(SiteEntity operator, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();


    // false if operators wasn't deleted
    boolean deleteAllByOperator(int operatorId, int userId);

    // false if operators wasn't deleted
    boolean deleteAllByOperator(int operatorId);


    // false if operators wasn't deleted
    boolean deleteAllByRegion(int regionId, int userId);

    // false if operators wasn't deleted
    boolean deleteAllByRegion(int regionId);

    // false if operators wasn't deleted
    boolean deleteAllByComments(String comments, int userId);

    // false if operators wasn't deleted
    boolean deleteAllByComments(String comments);

    // false if not found
    SiteEntity get(int id);

    // null if operator do not belong to userId
    SiteEntity get(int id, int userId);

    // false if not found
    SiteEntity getWithOs(int id);

    // null if operator do not belong to userId
    SiteEntity getWithOs(int id, int userId);

    // ORDERED Operator ASC
    List<SiteEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<SiteEntity> getAll();

    // ORDERED Operator ASC
    List<SiteEntity> getAllWithOs(int userId);

    // ORDERED Operator ASC
    List<SiteEntity> getAllWithOs();

    default SiteEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

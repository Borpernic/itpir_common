package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.ContactsAdEntity;

import java.util.List;

public interface ContactsAdRepository {

    // null if updated operator do not belong to userId
    ContactsAdEntity save(ContactsAdEntity entity, int userId);

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
    ContactsAdEntity get(int id);

    // null if operator do not belong to userId
    ContactsAdEntity get(int id, int userId);

    // false if not found
    ContactsAdEntity getWithOs(int id);

    // null if operator do not belong to userId
    ContactsAdEntity getWithOs(int id, int userId);

    // ORDERED Operator ASC
    List<ContactsAdEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<ContactsAdEntity> getAll();

    // ORDERED Operator ASC
    List<ContactsAdEntity> getAllWithOs(int userId);

    // ORDERED Operator ASC
    List<ContactsAdEntity> getAllWithOs();

    default ContactsAdEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

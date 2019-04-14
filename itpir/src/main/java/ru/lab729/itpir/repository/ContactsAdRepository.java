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
    boolean deleteAllBySite(int siteId, int userId);

    // false if operators wasn't deleted
    boolean deleteAllBySite(int siteId);


    // false if operators wasn't deleted
    boolean deleteAllByText(String text, int userId);

    // false if operators wasn't deleted
    boolean deleteAllByText(String text);

    // false if not found
    ContactsAdEntity get(int id);

    // null if operator do not belong to userId
    ContactsAdEntity get(int id, int userId);


    // false if not found
    List<ContactsAdEntity> getAllByText(String text);

    // null if operator do not belong to userId
    List<ContactsAdEntity> getAllByText(String text, int userId);

    // ORDERED Operator ASC
    List<ContactsAdEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<ContactsAdEntity> getAll();


    default ContactsAdEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

package ru.lab729.itpir.service;

import ru.lab729.itpir.model.ContactsAdEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface ContactsAdService {
    ContactsAdEntity get(int id) throws NotFoundException;

    ContactsAdEntity get(int id, int userId) throws NotFoundException;

    ContactsAdEntity getWithUser(int id, int userId);

    List<ContactsAdEntity> getAll();

    List<ContactsAdEntity> getAll(int userId);

    List<ContactsAdEntity> getAllByText(String text);

    List<ContactsAdEntity> getAllByText(String text, int userId);


    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    void deleteAllBySite(int siteId, int userId) throws NotFoundException;

    void deleteAllBySite(int siteId) throws NotFoundException;

    void deleteAllByText(String text, int userId) throws NotFoundException;

    void deleteAllByText(String text) throws NotFoundException;

    ContactsAdEntity update(ContactsAdEntity entity) throws NotFoundException;

    ContactsAdEntity update(ContactsAdEntity entity, int userId) throws NotFoundException;

    ContactsAdEntity create(ContactsAdEntity entity, int userId);


}
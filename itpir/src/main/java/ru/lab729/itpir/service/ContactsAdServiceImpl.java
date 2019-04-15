package ru.lab729.itpir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.lab729.itpir.model.ContactsAdEntity;
import ru.lab729.itpir.repository.ContactsAdRepository;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

import static ru.lab729.itpir.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ContactsAdServiceImpl implements ContactsAdService {

    private final ContactsAdRepository repository;

    @Autowired
    public ContactsAdServiceImpl(ContactsAdRepository repository) {
        this.repository = repository;
    }


    @Override
    public ContactsAdEntity create(ContactsAdEntity entity, int userId) {
        Assert.notNull(entity, "operator must not be null");
        return repository.save(entity, userId);
    }


    @Override
    public ContactsAdEntity update(ContactsAdEntity entity) throws NotFoundException {
        Integer userId = get(entity.getId()).getUser().getId();
        return checkNotFoundWithId(repository.save(entity, userId), entity.getId(), "update without id");
    }

    @Override
    public ContactsAdEntity update(ContactsAdEntity entity, int userId) {
        return checkNotFoundWithId(repository.save(entity, userId), entity.getId());
    }

    @Override
    public ContactsAdEntity get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public ContactsAdEntity get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<ContactsAdEntity> getAll() {

        return repository.getAll();
    }

    @Override
    public List<ContactsAdEntity> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<ContactsAdEntity> getAllByText(String text) {
        return repository.getAllByText(text);
    }

    @Override
    public List<ContactsAdEntity> getAllByText(String text, int userId) {
        return repository.getAllByText(text, userId);
    }


    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public void deleteAll(int userId) throws NotFoundException {

        checkNotFoundWithId(repository.deleteAll(userId), 0);
    }

    @Override
    public void deleteAll() throws NotFoundException {
        checkNotFoundWithId(repository.deleteAll(), 0);
    }

    @Override
    public void deleteAllBySite(int siteId, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.deleteAllBySite(siteId, userId), 0);


    }

    @Override
    public void deleteAllBySite(int siteId) throws NotFoundException {
        checkNotFoundWithId(repository.deleteAllBySite(siteId), 0);
    }

    @Override
    public void deleteAllByText(String text, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.deleteAllByText(text, userId), 0);
    }

    @Override
    public void deleteAllByText(String text) throws NotFoundException {
        checkNotFoundWithId(repository.deleteAllByText(text), 0);
    }

    @Override
    public ContactsAdEntity getWithUser(int id, int userId) {
        return checkNotFoundWithId(repository.getWithUser(id, userId), id);
    }


}
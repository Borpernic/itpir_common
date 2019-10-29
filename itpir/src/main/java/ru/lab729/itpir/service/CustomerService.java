package ru.lab729.itpir.service;

import ru.lab729.itpir.model.CustomerEntity;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerEntity get(int id) throws NotFoundException;

    CustomerEntity get(int id, int userId) throws NotFoundException;

    CustomerEntity getWithUser(int id, int userId);

    List<CustomerEntity> getAll();

    List<CustomerEntity> getAll(int userId);

    void delete(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void deleteAll() throws NotFoundException;

    CustomerEntity update(CustomerEntity entity) throws NotFoundException;

    CustomerEntity update(CustomerEntity entity, int userId) throws NotFoundException;

    CustomerEntity create(CustomerEntity entity, int userId);
}
package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.CustomerEntity;

import java.util.List;

public interface CustomerRepository {

    // null if updated operator do not belong to userId
    CustomerEntity save(CustomerEntity entity, int userId);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean delete(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAll(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();

    // false if not found
    CustomerEntity get(int id);

    // null if operator do not belong to userId
    CustomerEntity get(int id, int userId);

    // ORDERED Operator ASC
    List<CustomerEntity> getAll(int userId);

    // ORDERED Operator ASC
    List<CustomerEntity> getAll();

    default CustomerEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

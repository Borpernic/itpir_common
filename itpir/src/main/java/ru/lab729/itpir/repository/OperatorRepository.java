package ru.lab729.itpir.repository;

import org.springframework.data.repository.query.Param;
import ru.lab729.itpir.model.OperatorEntity;

import java.util.List;

public interface OperatorRepository {

    // null if updated operator do not belong to userId
    OperatorEntity saveWithUserId(OperatorEntity operator, int userId);

    OperatorEntity save(OperatorEntity operator);

    // false if not found
    boolean delete(int id);

    // false if not found
    boolean deleteByIdAndUserId(int id, int userId);

    // false if operators wasn't deleted
    boolean deleteAllByUserId(int userId);

    // false if operators wasn't deleted
    boolean deleteAll();



    // false if not found
    OperatorEntity get(int id);

    // null if operator do not belong to userId
    OperatorEntity getByIdAndUserId(int id, int userId);

    // ORDERED Operator ASC
    List<OperatorEntity> getAllByUserId(int userId);

    // ORDERED Operator ASC
    List<OperatorEntity> getAll();

    default OperatorEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

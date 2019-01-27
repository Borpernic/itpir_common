package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.Meal;
import ru.lab729.itpir.model.OperatorEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OperatorRepository {
    // null if updated operator do not belong to userId when enable save mode
    OperatorEntity save(OperatorEntity operator, int userId, boolean saveMode);

    // false if operator do not belong to userId when enable save mode
    boolean delete(int id, int userId, boolean saveMode);

    // false if operator do not belong to userId when enable save mode
    boolean deleteAll(int id, int userId, boolean saveMode);

    // false if operator do not belong to userId when enable save mode
    boolean deleteAllByUser(int id, int userId, boolean saveMode);

    // null if operator do not belong to userId when enable save mode
    OperatorEntity get(int id, int userId, boolean saveMode);

    // ORDERED id ASC
    List<OperatorEntity> getAllByUser(int userId);

    // ORDERED id ASC
    List<OperatorEntity> getAll();

    default OperatorEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

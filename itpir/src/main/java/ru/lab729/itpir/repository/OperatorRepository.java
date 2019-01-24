package ru.lab729.itpir.repository;

import ru.lab729.itpir.model.Meal;
import ru.lab729.itpir.model.OperatorEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OperatorRepository {
    // null if updated meal do not belong to userId
    OperatorEntity save(OperatorEntity operator, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    OperatorEntity get(int id, int userId);

    // ORDERED dateTime desc
    List<OperatorEntity> getAll(int userId);

    // ORDERED dateTime desc
    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    default OperatorEntity getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}

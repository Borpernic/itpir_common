package ru.lab729.itpir.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.Meal;
import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.repository.MealRepository;
import ru.lab729.itpir.repository.OperatorRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaOperatorRepositoryImpl implements OperatorRepository {

    @Autowired
    private CrudMealRepository crudOperatorRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public OperatorEntity save(OperatorEntity operator, int userId) {
        if (!operator.isNew() && get(operator.getId(), userId) == null) {
            return null;
        }
        OperatorEntity.setUser(crudUserRepository.getOne(userId));
        return crudOperatorRepository.save(operator);
    }


    @Override
    public boolean delete(int id, int userId) {
        return crudMealRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudMealRepository.findById(id).filter(meal -> meal.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudMealRepository.getAll(userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudMealRepository.getBetween(startDate, endDate, userId);
    }

    @Override
    public Meal getWithUser(int id, int userId) {
        return crudMealRepository.getWithUser(id, userId);
    }
}

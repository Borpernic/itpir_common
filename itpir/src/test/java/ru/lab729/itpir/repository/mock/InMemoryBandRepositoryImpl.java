package ru.lab729.itpir.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.lab729.itpir.model.BandEntity;
import ru.lab729.itpir.model.Meal;
import ru.lab729.itpir.repository.BandRepository;
import ru.lab729.itpir.repository.MealRepository;
import ru.lab729.itpir.util.Util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryBandRepositoryImpl implements BandRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryBandRepositoryImpl.class);

    // Map  userId -> (mealId-> meal)
    private Map<Integer, Map<Integer, BandEntity>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public BandEntity save(BandEntity entity, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public boolean deleteAll(int userId) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public BandEntity get(int id) {
        return null;
    }

    @Override
    public BandEntity get(int id, int userId) {
        return null;
    }

    @Override
    public List<BandEntity> getAll(int userId) {
        return null;
    }

    @Override
    public List<BandEntity> getAll() {
        return null;
    }
}
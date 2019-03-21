package ru.lab729.itpir.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lab729.itpir.model.Meal;
import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.repository.MealRepository;
import ru.lab729.itpir.repository.OperatorRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcOperatorRepositoryImpl implements OperatorRepository {

    private static final RowMapper<OperatorEntity> ROW_MAPPER = BeanPropertyRowMapper.newInstance(OperatorEntity.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertOperator;

    @Autowired
    public JdbcOperatorRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertOperator = new SimpleJdbcInsert(dataSource)
                .withTableName("operator")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public OperatorEntity save(OperatorEntity operatorEntity, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", operatorEntity.getId())
                .addValue("operator", operatorEntity.getOperator())
                .addValue("comments", operatorEntity.getComments())
                .addValue("user_id", userId);

        if (operatorEntity.isNew()) {
            Number newId = insertOperator.executeAndReturnKey(map);
            operatorEntity.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE meals " +
                            "   SET description=:description, calories=:calories, date_time=:date_time " +
                            " WHERE id=:id AND user_id=:user_id"
                    , map) == 0) {
                return null;
            }
        }
        return operatorEntity;
    }


    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM operator WHERE id=? AND user_id=?", id, userId) != 0;
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
    public OperatorEntity get(int id) {
        return null;
    }

    @Override
    public OperatorEntity get(int id, int userId) {
        List<OperatorEntity> meals = jdbcTemplate.query(
                "SELECT * FROM operator WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<OperatorEntity> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM operator WHERE user_id=? ORDER BY operator.operator DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<OperatorEntity> getAll() {
        return null;
    }

}

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
import ru.lab729.itpir.model.DateChangeStatusEntity;
import ru.lab729.itpir.repository.DateChangeStatusRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcDateChangeStatusRepositoryImpl implements DateChangeStatusRepository {

    private static final RowMapper<DateChangeStatusEntity> ROW_MAPPER = BeanPropertyRowMapper.newInstance(DateChangeStatusEntity.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertOperator;

    @Autowired
    public JdbcDateChangeStatusRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertOperator = new SimpleJdbcInsert(dataSource)
                .withTableName("activity")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public DateChangeStatusEntity save(DateChangeStatusEntity entity, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("activity", entity.getActivity())
                .addValue("date_time", entity.getDate())
                .addValue("status_activity", entity.getStatusActivity())
                .addValue("comments", entity.getComments())
                .addValue("user_id", userId);

        if (entity.isNew()) {
            Number newId = insertOperator.executeAndReturnKey(map);
            entity.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE date_change_status " +
                            "   SET " +
                            "activity=:activity, " +
                            "date_time=:date_time, " +
                            "status_activity=:status_activity, " +
                            "comments=:comments " +
                            " WHERE id=:id AND user_id=:user_id"
                    , map) == 0) {
                return null;
            }
        }
        return entity;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM date_change_status WHERE id=? AND user_id=?", id, userId) != 0;
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
    public DateChangeStatusEntity get(int id) {
        return null;
    }

    @Override
    public DateChangeStatusEntity get(int id, int userId) {
        List<DateChangeStatusEntity> meals = jdbcTemplate.query(
                "SELECT * FROM date_change_status WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<DateChangeStatusEntity> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM date_change_status WHERE user_id=? ORDER BY date_change_status.id desc ", ROW_MAPPER, userId);
    }

    @Override
    public List<DateChangeStatusEntity> getAll() {
        return null;
    }

}

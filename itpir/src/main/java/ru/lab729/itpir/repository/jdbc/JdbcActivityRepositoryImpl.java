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
import ru.lab729.itpir.model.ActivityEntity;
import ru.lab729.itpir.repository.ActivityRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcActivityRepositoryImpl implements ActivityRepository {

    private static final RowMapper<ActivityEntity> ROW_MAPPER = BeanPropertyRowMapper.newInstance(ActivityEntity.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertOperator;

    @Autowired
    public JdbcActivityRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertOperator = new SimpleJdbcInsert(dataSource)
                .withTableName("activity")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public ActivityEntity save(ActivityEntity entity, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("os", entity.getOs())
                .addValue("implementer", entity.getImplementer())
                .addValue("type_activity", entity.getTypeActivity())
                .addValue("date_time", entity.getDateTime())
                .addValue("plane_date_time", entity.getPlaneDateTime())
                .addValue("rating", entity.getRating())
                .addValue("accept", entity.getAccept())
                .addValue("accept_date_time", entity.getAcceptDateTime())
                .addValue("status_activity", entity.getStatusActivity())
                .addValue("date_change_status", entity.getDateTimeChangeStatus())
                .addValue("comments", entity.getComments())
                .addValue("user_id", userId);

        if (entity.isNew()) {
            Number newId = insertOperator.executeAndReturnKey(map);
            entity.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE activity " +
                            "   SET " +
                            "os=:os, " +
                            "implementer=:implementer, " +
                            "type_activity=:type_activity, " +
                            "date_time=:date_time, " +
                            "plane_date_time=:plane_date_time, " +
                            "rating=:rating, " +
                            "accept=:accept, " +
                            "accept_date_time=:accept_date_time, " +
                            "status_activity=:status_activity, " +
                            "date_time_change_status=:date_time_change_status, " +
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
        return jdbcTemplate.update("DELETE FROM activity WHERE id=? AND user_id=?", id, userId) != 0;
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
    public ActivityEntity get(int id) {
        return null;
    }

    @Override
    public ActivityEntity get(int id, int userId) {
        List<ActivityEntity> meals = jdbcTemplate.query(
                "SELECT * FROM activity WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<ActivityEntity> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM activity WHERE user_id=? ORDER BY activity.id ASC ", ROW_MAPPER, userId);
    }

    @Override
    public List<ActivityEntity> getAll() {
        return null;
    }

}

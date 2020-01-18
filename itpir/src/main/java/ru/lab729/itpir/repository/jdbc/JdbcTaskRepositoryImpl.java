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
import ru.lab729.itpir.model.TaskEntity;
import ru.lab729.itpir.repository.TaskRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcTaskRepositoryImpl implements TaskRepository {

    private static final RowMapper<TaskEntity> ROW_MAPPER = BeanPropertyRowMapper.newInstance(TaskEntity.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertOperator;

    @Autowired
    public JdbcTaskRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertOperator = new SimpleJdbcInsert(dataSource)
                .withTableName("activity")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public TaskEntity save(TaskEntity entity, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("department", entity.getDepartment())
                .addValue("approve", entity.getApprove())
                .addValue("approve_date_time", entity.getApproveDate())
                .addValue("activity", entity.getActivity())
                .addValue("date_time", entity.getDate())
                .addValue("plane_date_time", entity.getPlaneDate())
                .addValue("type_task", entity.getTypeTask())
                .addValue("result_task", entity.getResultTask())
                .addValue("right_on_time", entity.getRightOnTime())
                .addValue("comments", entity.getComments())
                .addValue("user_id", userId);

        if (entity.isNew()) {
            Number newId = insertOperator.executeAndReturnKey(map);
            entity.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE task " +
                            "   SET " +
                            "department=:department, " +
                            "approve=:approve, " +
                            "approve_date_time=:approve_date_time, " +
                            "activity=:activity, " +
                            "date_time=:date_time, " +
                            "plane_date_time=:plane_date_time, " +
                            "type_task=:type_task, " +
                            "result_task=:result_task, " +
                            "right_on_time=:right_on_time, " +
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
    public TaskEntity get(int id) {
        return null;
    }

    @Override
    public TaskEntity get(int id, int userId) {
        List<TaskEntity> meals = jdbcTemplate.query(
                "SELECT * FROM activity WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<TaskEntity> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM activity WHERE user_id=? ORDER BY activity.id ASC ", ROW_MAPPER, userId);
    }

    @Override
    public List<TaskEntity> getAll() {
        return null;
    }

}

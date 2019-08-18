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
import ru.lab729.itpir.model.ProjectEntity;
import ru.lab729.itpir.repository.ProjectRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcProjectRepositoryImpl implements ProjectRepository {

    private static final RowMapper<ProjectEntity> ROW_MAPPER = BeanPropertyRowMapper.newInstance(ProjectEntity.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertOperator;

    @Autowired
    public JdbcProjectRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertOperator = new SimpleJdbcInsert(dataSource)
                .withTableName("project")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public ProjectEntity save(ProjectEntity entity, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("pm", entity.getPm())
                .addValue("customer", entity.getCustomer())
                .addValue("comments", entity.getComments())
                .addValue("user_id", userId);

        if (entity.isNew()) {
            Number newId = insertOperator.executeAndReturnKey(map);
            entity.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE project " +
                            " SET pm=:pm, comments=:comments , customer=:customer" +
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
        return jdbcTemplate.update("DELETE FROM project WHERE id=? AND user_id=?", id, userId) != 0;
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
    public ProjectEntity get(int id) {
        return null;
    }

    @Override
    public ProjectEntity get(int id, int userId) {
        List<ProjectEntity> meals = jdbcTemplate.query(
                "SELECT * FROM project WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<ProjectEntity> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM project WHERE user_id=? ORDER BY pm ASC ", ROW_MAPPER, userId);
    }

    @Override
    public List<ProjectEntity> getAll() {
        return null;
    }

}

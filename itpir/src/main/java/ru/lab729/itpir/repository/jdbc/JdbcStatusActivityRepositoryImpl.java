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
import ru.lab729.itpir.model.StatusActivityEntity;
import ru.lab729.itpir.repository.StatusActivityRepository;
import ru.lab729.itpir.repository.StatusOsRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcStatusActivityRepositoryImpl implements StatusActivityRepository {

    private static final RowMapper<StatusActivityEntity> ROW_MAPPER = BeanPropertyRowMapper.newInstance(StatusActivityEntity.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertOperator;

    @Autowired
    public JdbcStatusActivityRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertOperator = new SimpleJdbcInsert(dataSource)
                .withTableName("status_activity")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public StatusActivityEntity save(StatusActivityEntity entity, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("status", entity.getStatus())
                             .addValue("user_id", userId);

        if (entity.isNew()) {
            Number newId = insertOperator.executeAndReturnKey(map);
            entity.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE status_activity " +
                            "   SET status=:status" +
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
        return jdbcTemplate.update("DELETE FROM status_activity WHERE id=? AND user_id=?", id, userId) != 0;
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
    public StatusActivityEntity get(int id) {
        return null;
    }

    @Override
    public StatusActivityEntity get(int id, int userId) {
        List<StatusActivityEntity> entity = jdbcTemplate.query(
                "SELECT * FROM status_activity WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(entity);
    }

    @Override
    public List<StatusActivityEntity> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM status_activity WHERE user_id=? ORDER BY status ASC ", ROW_MAPPER, userId);
    }

    @Override
    public List<StatusActivityEntity> getAll() {
        return null;
    }

}

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
import ru.lab729.itpir.model.StatusOsEntity;
import ru.lab729.itpir.model.TypeOsEntity;
import ru.lab729.itpir.repository.StatusOsRepository;
import ru.lab729.itpir.repository.TypeOsRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcTypeOsRepositoryImpl implements TypeOsRepository {

    private static final RowMapper<TypeOsEntity> ROW_MAPPER = BeanPropertyRowMapper.newInstance(TypeOsEntity.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertOperator;

    @Autowired
    public JdbcTypeOsRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertOperator = new SimpleJdbcInsert(dataSource)
                .withTableName("type_os")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public TypeOsEntity save(TypeOsEntity entity, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("type", entity.getType())
                             .addValue("user_id", userId);

        if (entity.isNew()) {
            Number newId = insertOperator.executeAndReturnKey(map);
            entity.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE type_os " +
                            "   SET type=:type" +
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
        return jdbcTemplate.update("DELETE FROM type_os WHERE id=? AND user_id=?", id, userId) != 0;
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
    public TypeOsEntity get(int id) {
        return null;
    }

    @Override
    public TypeOsEntity get(int id, int userId) {
        List<TypeOsEntity> meals = jdbcTemplate.query(
                "SELECT * FROM type_os WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<TypeOsEntity> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM type_os WHERE user_id=? ORDER BY type ASC ", ROW_MAPPER, userId);
    }

    @Override
    public List<TypeOsEntity> getAll() {
        return null;
    }

}

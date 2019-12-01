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
import ru.lab729.itpir.model.TzpEntity;
import ru.lab729.itpir.repository.TzpRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcTzpRepositoryImpl implements TzpRepository {

    private static final RowMapper<TzpEntity> ROW_MAPPER = BeanPropertyRowMapper.newInstance(TzpEntity.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertOperator;

    @Autowired
    public JdbcTzpRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertOperator = new SimpleJdbcInsert(dataSource)
                .withTableName("tzp")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public TzpEntity save(TzpEntity entity, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("tzp", entity.getTzp())
                .addValue("razmernost", entity.getRazmernost())
                .addValue("price", entity.getPrice())
                .addValue("type_os", entity.getTypeOs())
                .addValue("type_implementer", entity.getTypeImplementer())
                .addValue("comments", entity.getComments())
                .addValue("user_id", userId);

        if (entity.isNew()) {
            Number newId = insertOperator.executeAndReturnKey(map);
            entity.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE tzp " +
                            " SET " +
                            "tzp=:tzp, " +
                            "razmernost=:razmernost, " +
                            "price=:price, " +
                            "type_os=:type_os, " +
                            "type_implementer=:type_implementer, " +
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
        return jdbcTemplate.update("DELETE FROM tzp WHERE id=? AND user_id=?", id, userId) != 0;
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
    public TzpEntity get(int id) {
        return null;
    }

    @Override
    public TzpEntity get(int id, int userId) {
        List<TzpEntity> meals = jdbcTemplate.query(
                "SELECT * FROM tzp WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }


    @Override
    public List<TzpEntity> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM tzp WHERE user_id=? ORDER BY tzp ASC", ROW_MAPPER, userId);
    }

    @Override
    public List<TzpEntity> getAll() {
        return null;
    }
}

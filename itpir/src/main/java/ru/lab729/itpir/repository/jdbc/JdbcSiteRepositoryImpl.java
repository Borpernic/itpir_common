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
import ru.lab729.itpir.model.SiteEntity;
import ru.lab729.itpir.repository.SiteRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcSiteRepositoryImpl implements SiteRepository {

    private static final RowMapper<SiteEntity> ROW_MAPPER = BeanPropertyRowMapper.newInstance(SiteEntity.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertOperator;

    @Autowired
    public JdbcSiteRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertOperator = new SimpleJdbcInsert(dataSource)
                .withTableName("site")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public SiteEntity save(SiteEntity entity, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("number", entity.getOperator())
                .addValue("comments", entity.getComments())
                .addValue("user_id", userId);

        if (entity.isNew()) {
            Number newId = insertOperator.executeAndReturnKey(map);
            entity.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE site " +
                            "   SET number=:number, comments=:comments " +
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
        return jdbcTemplate.update("DELETE FROM site WHERE id=? AND user_id=?", id, userId) != 0;
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
    public boolean deleteAllByOperator(int operatorId, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllByOperator(int operatorId) {
        return false;
    }

    @Override
    public boolean deleteAllByRegion(int regionId, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllByRegion(int regionId) {
        return false;
    }

    @Override
    public boolean deleteAllByComments(String comments, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllByComments(String comments) {
        return false;
    }

    @Override
    public SiteEntity get(int id) {
        return null;
    }

    @Override
    public SiteEntity get(int id, int userId) {
        List<SiteEntity> meals = jdbcTemplate.query(
                "SELECT * FROM site WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public SiteEntity getWithOs(int id) {
        return null;
    }

    @Override
    public SiteEntity getWithOs(int id, int userId) {
        return null;
    }

    @Override
    public List<SiteEntity> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM site WHERE user_id=? ORDER BY number ASC", ROW_MAPPER, userId);
    }

    @Override
    public List<SiteEntity> getAll() {
        return null;
    }

    @Override
    public List<SiteEntity> getAllWithOs(int userId) {
        return null;
    }

    @Override
    public List<SiteEntity> getAllWithOs() {
        return null;
    }

}

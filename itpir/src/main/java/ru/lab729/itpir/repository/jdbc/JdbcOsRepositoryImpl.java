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
import ru.lab729.itpir.model.OsEntity;
import ru.lab729.itpir.repository.OsRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcOsRepositoryImpl implements OsRepository {

    private static final RowMapper<OsEntity> ROW_MAPPER = BeanPropertyRowMapper.newInstance(OsEntity.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertOperator;

    @Autowired
    public JdbcOsRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertOperator = new SimpleJdbcInsert(dataSource)
                .withTableName("Curator")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public OsEntity save(OsEntity entity, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("site", entity.getSite())
                .addValue("internal_number", entity.getInternalNumber())
                .addValue("date_time", entity.getDate())
                .addValue("curator", entity.getCurator())
                .addValue("band", entity.getBand())
                .addValue("type_os", entity.getTypeOs())
                .addValue("type_BS", entity.getTypeBs())
                .addValue("type_AMS", entity.getTypeAms())
                .addValue("type_AFS", entity.getTypeAfs())
                .addValue("status_os", entity.getStatusOs())
                .addValue("comments", entity.getComments())
                .addValue("user_id", userId);

        if (entity.isNew()) {
            Number newId = insertOperator.executeAndReturnKey(map);
            entity.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE os " +
                            "   SET site=:site, " +
                            "date_time=:date_time , " +
                            "internal_number=: internal_number, " +
                            "curator=: curator, " +
                            "band=: band, " +
                            "type_os=: type_os, " +
                            "type_BS=: type_BS, " +
                            "type_AMS=: type_AMS, " +
                            "type_AFS=: type_AFS, " +
                            "status_os=: status_os, " +
                            "comments=: comments " +
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
        return jdbcTemplate.update("DELETE FROM os WHERE id=? AND user_id=?", id, userId) != 0;
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
    public OsEntity get(int id) {
        return null;
    }

    @Override
    public OsEntity get(int id, int userId) {
        List<OsEntity> meals = jdbcTemplate.query(
                "SELECT * FROM os WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<OsEntity> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM os WHERE user_id=? ORDER BY pm ASC ", ROW_MAPPER, userId);
    }

    @Override
    public List<OsEntity> getAll() {
        return null;
    }

}

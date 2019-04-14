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
import ru.lab729.itpir.model.ContactsAdEntity;
import ru.lab729.itpir.repository.ContactsAdRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcContactsAdRepositoryImpl implements ContactsAdRepository {

    private static final RowMapper<ContactsAdEntity> ROW_MAPPER = BeanPropertyRowMapper.newInstance(ContactsAdEntity.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertOperator;

    @Autowired
    public JdbcContactsAdRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertOperator = new SimpleJdbcInsert(dataSource)
                .withTableName("contacts_ad")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public ContactsAdEntity save(ContactsAdEntity entity, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("surname", entity.getSurname())
                .addValue("site_id", entity.getSite().getId())
                .addValue("status", entity.getStatus().getId())
                .addValue("user_id", userId);

        if (entity.isNew()) {
            Number newId = insertOperator.executeAndReturnKey(map);
            entity.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE contacts_ad " +
                            "   SET site_id=:siteId, surname=:surname, status=:status " +
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
        return jdbcTemplate.update("DELETE FROM contacts_ad WHERE id=? AND user_id=?", id, userId) != 0;
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
    public boolean deleteAllBySite(int siteId, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllBySite(int siteId) {
        return false;
    }

    @Override
    public boolean deleteAllByText(String text, int userId) {
        return false;
    }

    @Override
    public boolean deleteAllByText(String text) {
        return false;
    }

    @Override
    public ContactsAdEntity get(int id) {
        return null;
    }

    @Override
    public ContactsAdEntity get(int id, int userId) {
        List<ContactsAdEntity> meals = jdbcTemplate.query(
                "SELECT * FROM contacts_ad WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<ContactsAdEntity> getAllByText(String text) {
        return null;
    }

    @Override
    public List<ContactsAdEntity> getAllByText(String text, int userId) {
        return null;
    }


    @Override
    public List<ContactsAdEntity> getAll(int userId) {
        return null;
    }

    @Override
    public List<ContactsAdEntity> getAll() {
        return null;
    }

}

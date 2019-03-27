package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.TypeOsEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.TypeOsTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractTypeOsServiceTest extends AbstractServiceTest {

    @Autowired
    protected TypeOsService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), TYPE_OSS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_TYPE_OSS);
    }

    @Test
    void get() {
        TypeOsEntity actual = service.get(TYPE_OS1_ID, USER_ID);
        assertMatch(actual, TYPE_OS1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(TYPE_OS1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        TypeOsEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), TYPE_OS3, TYPE_OS2, TYPE_OS1, created);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new TypeOsEntity(null, "Модернизация"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        TypeOsEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(TYPE_OS1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        TypeOsEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(TYPE_OS1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(TYPE_OS1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(TYPE_OS1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(TYPE_OS1_ID, USER_ID);
        assertMatch(service.getAll(), TYPE_OS3,  ADMIN_TYPE_OS4, TYPE_OS2,ADMIN_TYPE_OS5);
    }

    @Test
    void delete() {
        service.delete(TYPE_OS1_ID);
        assertMatch(service.getAll(), TYPE_OS3,  ADMIN_TYPE_OS4, TYPE_OS2,ADMIN_TYPE_OS5);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(TYPE_OS1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(TYPE_OS1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(TYPE_OS1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<TypeOsEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_TYPE_OS4, ADMIN_TYPE_OS5);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_TYPE_OS4, ADMIN_TYPE_OS5);
    }


/*

   @Test
    void getBetween() {
        assertMatch(service.getBetweenDates(
                LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID), MEAL3, MEAL2, MEAL1);
    }
*/

    @Test
    void testValidation() {
        assumeTrue(isJpaBased());
        validateRootCause(() -> service.create(new TypeOsEntity(null, ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeOsEntity(null, "T"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeOsEntity(null, null), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeOsEntity(null, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444"), USER_ID), ConstraintViolationException.class);
    }
}
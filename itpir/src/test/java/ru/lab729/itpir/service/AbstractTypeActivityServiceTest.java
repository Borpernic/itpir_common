package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.TypeActivityEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.TypeActivityTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractTypeActivityServiceTest extends AbstractServiceTest {

    @Autowired
    protected TypeActivityService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), TYPE_ACTIVITYS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_TYPE_ACTIVITYS);
    }

    @Test
    void get() {
        TypeActivityEntity actual = service.get(TYPE_ACTIVITY1_ID, USER_ID);
        assertMatch(actual, TYPE_ACTIVITY1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(TYPE_ACTIVITY1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        TypeActivityEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), TYPE_ACTIVITY1, TYPE_ACTIVITY2, created);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new TypeActivityEntity(null, "Обследование", false, false, false, true, false, false, false, false, false, false, "Duplicate"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        TypeActivityEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(TYPE_ACTIVITY1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        TypeActivityEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(TYPE_ACTIVITY1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(TYPE_ACTIVITY1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(TYPE_ACTIVITY1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(TYPE_ACTIVITY1_ID, USER_ID);
        assertMatch(service.getAll(), TYPE_ACTIVITY2,
                ADMIN_TYPE_ACTIVITY3, ADMIN_TYPE_ACTIVITY4, ADMIN_TYPE_ACTIVITY5,
                ADMIN_TYPE_ACTIVITY6, ADMIN_TYPE_ACTIVITY7, ADMIN_TYPE_ACTIVITY8, ADMIN_TYPE_ACTIVITY9, ADMIN_TYPE_ACTIVITY10, ADMIN_TYPE_ACTIVITY11);
    }

    @Test
    void delete() {
        service.delete(TYPE_ACTIVITY1_ID);
        assertMatch(service.getAll(), TYPE_ACTIVITY2,
                ADMIN_TYPE_ACTIVITY3, ADMIN_TYPE_ACTIVITY4, ADMIN_TYPE_ACTIVITY5,
                ADMIN_TYPE_ACTIVITY6, ADMIN_TYPE_ACTIVITY7, ADMIN_TYPE_ACTIVITY8, ADMIN_TYPE_ACTIVITY9, ADMIN_TYPE_ACTIVITY10, ADMIN_TYPE_ACTIVITY11);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(TYPE_ACTIVITY1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(TYPE_ACTIVITY1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(TYPE_ACTIVITY1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<TypeActivityEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_TYPE_ACTIVITY3, ADMIN_TYPE_ACTIVITY4, ADMIN_TYPE_ACTIVITY5,
                ADMIN_TYPE_ACTIVITY6, ADMIN_TYPE_ACTIVITY7, ADMIN_TYPE_ACTIVITY8, ADMIN_TYPE_ACTIVITY9,
                ADMIN_TYPE_ACTIVITY10, ADMIN_TYPE_ACTIVITY11);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_TYPE_ACTIVITY3, ADMIN_TYPE_ACTIVITY4, ADMIN_TYPE_ACTIVITY5,
                ADMIN_TYPE_ACTIVITY6, ADMIN_TYPE_ACTIVITY7, ADMIN_TYPE_ACTIVITY8, ADMIN_TYPE_ACTIVITY9,
                ADMIN_TYPE_ACTIVITY10, ADMIN_TYPE_ACTIVITY11);
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
        validateRootCause(() -> service.create(new TypeActivityEntity(null, "", false, false, false, true, false, false, false, false, false, false, "Подразделение"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeActivityEntity(null, "С", false, false, false, true, false, false, false, false, false, false, "Подразделение"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeActivityEntity(null, null, false, false, false, true, false, false, false, false, false, false, "Подразделение"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeActivityEntity(null, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444", false, false, false, true, false, false, false, false, false, false, "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeActivityEntity(null, "Обследование+", false, false, false, true, false, false, false, false, false, false, ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeActivityEntity(null, "Обследование+", false, false, false, true, false, false, false, false, false, false, "C"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeActivityEntity(null, "Обследование+", false, false, false, true, false, false, false, false, false, false, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444"), USER_ID), ConstraintViolationException.class);

    }
}
package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.StatusActivityEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.StatusActivityTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractStatusActivityServiceTest extends AbstractServiceTest {

    @Autowired
    protected StatusActivityService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), STATUS_ACTIVITYS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_STATUS_ACTIVITYS);
    }

    @Test
    void get() {
        StatusActivityEntity actual = service.get(STATUS_ACTIVITY1_ID, USER_ID);
        assertMatch(actual, STATUS_ACTIVITY1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(STATUS_ACTIVITY1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        StatusActivityEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), STATUS_ACTIVITY2, STATUS_ACTIVITY1, created);
    }


    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new StatusActivityEntity(null, "Выполнено"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        StatusActivityEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(STATUS_ACTIVITY1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        StatusActivityEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(STATUS_ACTIVITY1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(STATUS_ACTIVITY1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(STATUS_ACTIVITY1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(STATUS_ACTIVITY1_ID, USER_ID);
        assertMatch(service.getAll(), STATUS_ACTIVITY2,ADMIN_STATUS_ACTIVITY3,  ADMIN_STATUS_ACTIVITY5,ADMIN_STATUS_ACTIVITY4);
    }

    @Test
    void delete() {
        service.delete(STATUS_ACTIVITY1_ID);
        assertMatch(service.getAll(), STATUS_ACTIVITY2,ADMIN_STATUS_ACTIVITY3,  ADMIN_STATUS_ACTIVITY5,ADMIN_STATUS_ACTIVITY4);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(STATUS_ACTIVITY1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(STATUS_ACTIVITY1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(STATUS_ACTIVITY1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<StatusActivityEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_STATUS_ACTIVITY3,  ADMIN_STATUS_ACTIVITY5,ADMIN_STATUS_ACTIVITY4);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_STATUS_ACTIVITY3,  ADMIN_STATUS_ACTIVITY5,ADMIN_STATUS_ACTIVITY4);
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
        validateRootCause(() -> service.create(new StatusActivityEntity(null, ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new StatusActivityEntity(null, "С"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new StatusActivityEntity(null, null), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new StatusActivityEntity(null, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444"), USER_ID), ConstraintViolationException.class);
    }
}
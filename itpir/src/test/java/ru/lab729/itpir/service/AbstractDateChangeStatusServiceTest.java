package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lab729.itpir.model.DateChangeStatusEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.ActivityTestData.ACTIVITY1;
import static ru.lab729.itpir.DateChangeStatusTestData.*;
import static ru.lab729.itpir.StatusActivityTestData.STATUS_ACTIVITY1;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractDateChangeStatusServiceTest extends AbstractServiceTest {

    @Autowired
    protected DateChangeStatusService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), DATE_CHANGE_STATUS1, DATE_CHANGE_STATUS2);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_DATE_CHANGE_STATUSS);
    }

    @Test
    void get() {
        DateChangeStatusEntity actual = service.get(DATE_CHANGE_STATUS1_ID, USER_ID);
        assertMatch(actual, DATE_CHANGE_STATUS1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(DATE_CHANGE_STATUS1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        DateChangeStatusEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), DATE_CHANGE_STATUS1, DATE_CHANGE_STATUS2, created);
    }

/*    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new DateChangeStatusEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 13, 0),
                STATUS_ACTIVITY1, "Новая задача 1"), USER_ID));
    }*/

    @Test
    void updateWithUserId() {
        DateChangeStatusEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(DATE_CHANGE_STATUS1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        DateChangeStatusEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(DATE_CHANGE_STATUS1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(DATE_CHANGE_STATUS1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(DATE_CHANGE_STATUS1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(DATE_CHANGE_STATUS1_ID, USER_ID);
        assertMatch(service.getAll(), DATE_CHANGE_STATUS2, ADMIN_DATE_CHANGE_STATUS3);
    }

    @Test
    void delete() {
        service.delete(DATE_CHANGE_STATUS1_ID);
        assertMatch(service.getAll(), DATE_CHANGE_STATUS2, ADMIN_DATE_CHANGE_STATUS3);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(DATE_CHANGE_STATUS1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(DATE_CHANGE_STATUS1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(DATE_CHANGE_STATUS1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<DateChangeStatusEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_DATE_CHANGE_STATUS3);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_DATE_CHANGE_STATUS3);
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
        validateRootCause(() -> service.create(new DateChangeStatusEntity(null, null, of(2019, Month.MAY, 30, 13, 0),
                STATUS_ACTIVITY1, "Задача 1"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new DateChangeStatusEntity(null, ACTIVITY1, null,
                STATUS_ACTIVITY1, "Задача 1"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new DateChangeStatusEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 13, 0),
                null, "Задача 1"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new DateChangeStatusEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 13, 0),
                        STATUS_ACTIVITY1,
                        "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901"),
                USER_ID), ConstraintViolationException.class);

    }
}
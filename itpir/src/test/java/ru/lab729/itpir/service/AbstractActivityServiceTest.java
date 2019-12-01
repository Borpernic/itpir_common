package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.ActivityEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.ActivityTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractActivityServiceTest extends AbstractServiceTest {

    @Autowired
    protected ActivityService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), ALL_ACTIVITYS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_ACTIVITYS);
    }

    @Test
    void get() {
        ActivityEntity actual = service.get(ACTIVITY1_ID, USER_ID);
        assertMatch(actual, ACTIVITY1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(ACTIVITY1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        ActivityEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), ACTIVITY2, ACTIVITY1, created);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new ActivityEntity(null, "U900", "Duplicate"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        ActivityEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(ACTIVITY1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        ActivityEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(ACTIVITY1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(ACTIVITY1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(ACTIVITY1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(ACTIVITY1_ID, USER_ID);
        assertMatch(service.getAll(), ADMIN_ACTIVITY3, ACTIVITY2);
    }

    @Test
    void delete() {
        service.delete(ACTIVITY1_ID);
        assertMatch(service.getAll(), ADMIN_ACTIVITY3, ACTIVITY2);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(ACTIVITY1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(ACTIVITY1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(ACTIVITY1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<ActivityEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_ACTIVITY3);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_ACTIVITY3);
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
        validateRootCause(() -> service.create(new ActivityEntity(null, "", "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ActivityEntity(null, "", "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ActivityEntity(null, null, "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ActivityEntity(null, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444", "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ActivityEntity(null, "G900", ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ActivityEntity(null, "G900", "C"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ActivityEntity(null, "G900", "Co"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ActivityEntity(null, "G900", "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444"), USER_ID), ConstraintViolationException.class);
    }
}
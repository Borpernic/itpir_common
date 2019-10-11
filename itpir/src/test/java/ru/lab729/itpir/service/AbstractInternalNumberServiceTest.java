package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.InternalNumberEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.InternalNumberTestData.*;
import static ru.lab729.itpir.ProjectTestData.PROJECT1;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractInternalNumberServiceTest extends AbstractServiceTest {

    @Autowired
    protected InternalNumberService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), INTERNAL_NUMBERS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_INTERNAL_NUMBERS);
    }

    @Test
    void get() {
        InternalNumberEntity actual = service.get(INTERNAL_NUMBER1_ID, USER_ID);
        assertMatch(actual, INTERNAL_NUMBER_1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(INTERNAL_NUMBER1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        InternalNumberEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), INTERNAL_NUMBER_1, INTERNAL_NUMBER_2, created);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new InternalNumberEntity(null, PROJECT1, "3568999"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        InternalNumberEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(INTERNAL_NUMBER1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        InternalNumberEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(INTERNAL_NUMBER1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(INTERNAL_NUMBER1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(INTERNAL_NUMBER1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(INTERNAL_NUMBER1_ID, USER_ID);
        assertMatch(service.getAll(), ADMIN_INTERNAL_NUMBER_3, INTERNAL_NUMBER_2);
    }

    @Test
    void delete() {
        service.delete(INTERNAL_NUMBER1_ID);
        assertMatch(service.getAll(), ADMIN_INTERNAL_NUMBER_3, INTERNAL_NUMBER_2);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(INTERNAL_NUMBER1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(INTERNAL_NUMBER1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(INTERNAL_NUMBER1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<InternalNumberEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_INTERNAL_NUMBER_3);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_INTERNAL_NUMBER_3);
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
        validateRootCause(() -> service.create(new InternalNumberEntity(null, PROJECT1, ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new InternalNumberEntity(null, PROJECT1, "1"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new InternalNumberEntity(null, PROJECT1, "12"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new InternalNumberEntity(null, PROJECT1, null), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new InternalNumberEntity(null, PROJECT1, "123456789012345678901"), USER_ID), ConstraintViolationException.class);
    }
}
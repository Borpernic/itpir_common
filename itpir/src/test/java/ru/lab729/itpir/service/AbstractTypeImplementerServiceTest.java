package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.TypeImplementerEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.TypeImplementerTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractTypeImplementerServiceTest extends AbstractServiceTest {

    @Autowired
    protected TypeImplementerService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), TYPE_IMPLEMENTERS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_TYPE_IMPLEMENTERS);
    }

    @Test
    void get() {
        TypeImplementerEntity actual = service.get(TYPE_IMPLEMENTER1_ID, USER_ID);
        assertMatch(actual, TYPE_IMPLEMENTER1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(TYPE_IMPLEMENTER1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        TypeImplementerEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), TYPE_IMPLEMENTER1, TYPE_IMPLEMENTER2, created);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new TypeImplementerEntity(null, "Оклад", "Duplicate"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        TypeImplementerEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(TYPE_IMPLEMENTER1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        TypeImplementerEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(TYPE_IMPLEMENTER1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(TYPE_IMPLEMENTER1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(TYPE_IMPLEMENTER1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(TYPE_IMPLEMENTER1_ID, USER_ID);
        assertMatch(service.getAll(), TYPE_IMPLEMENTER2, ADMIN_TYPE_IMPLEMENTER3);
    }

    @Test
    void delete() {
        service.delete(TYPE_IMPLEMENTER1_ID);
        assertMatch(service.getAll(), TYPE_IMPLEMENTER2, ADMIN_TYPE_IMPLEMENTER3);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(TYPE_IMPLEMENTER1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(TYPE_IMPLEMENTER1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(TYPE_IMPLEMENTER1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<TypeImplementerEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_TYPE_IMPLEMENTER3);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_TYPE_IMPLEMENTER3);
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
        validateRootCause(() -> service.create(new TypeImplementerEntity(null, "", "Штатный"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeImplementerEntity(null, "С", "Штатный"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeImplementerEntity(null, null, "Штатный"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeImplementerEntity(null, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444", "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeImplementerEntity(null, "Оклад+", ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeImplementerEntity(null, "Оклад+", "C"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeImplementerEntity(null, "Оклад+", "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444"), USER_ID), ConstraintViolationException.class);
    }
}
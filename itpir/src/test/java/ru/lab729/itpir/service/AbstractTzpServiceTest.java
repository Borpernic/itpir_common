package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.TzpEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.math.BigInteger;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.TypeImplementerTestData.TYPE_IMPLEMENTER1;
import static ru.lab729.itpir.TypeOsTestData.TYPE_OS2;
import static ru.lab729.itpir.TzpTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractTzpServiceTest extends AbstractServiceTest {

    @Autowired
    protected TzpService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), TZPS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_TZPS);
    }

    @Test
    void get() {
        TzpEntity actual = service.get(TZP1_ID, USER_ID);
        assertMatch(actual, TZP1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(TZP1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        TzpEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), TZP1, TZP2, TZP3, TZP4, TZP5, TZP6, TZP7, TZP8, TZP9, TZP10,
                TZP11, TZP12, TZP13, TZP14, TZP15, TZP16, TZP17, TZP18, TZP19, TZP20, TZP21, TZP22, created);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new TzpEntity(null,
                "1. Обследование площадки, сбор исходных данных, составление и согласование Акта обследования с Заказчиком при модернизации существующего объекта",
                "актенг", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        TzpEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(TZP1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        TzpEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(TZP1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(TZP1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(TZP1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(TZP1_ID, USER_ID);
        assertMatch(service.getAll(), TZP2, TZP3, TZP4, TZP5, TZP6, TZP7, TZP8, TZP9, TZP10,
                TZP11, TZP12, TZP13, TZP14, TZP15, TZP16, TZP17, TZP18, TZP19, TZP20, TZP21, TZP22, ADMIN_TZP23, ADMIN_TZP24);
    }

    @Test
    void delete() {
        service.delete(TZP1_ID);
        assertMatch(service.getAll(), TZP2, TZP3, TZP4, TZP5, TZP6, TZP7, TZP8, TZP9, TZP10,
                TZP11, TZP12, TZP13, TZP14, TZP15, TZP16, TZP17, TZP18, TZP19, TZP20, TZP21, TZP22, ADMIN_TZP23, ADMIN_TZP24);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(TZP1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(TZP1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(TZP1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<TzpEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_TZP23, ADMIN_TZP24);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_TZP23, ADMIN_TZP24);
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
        validateRootCause(() -> service.create(new TzpEntity(null, null, "акт", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TzpEntity(null, "", "акт", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TzpEntity(null, "Е", "акт", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TzpEntity(null, "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567891", "акт", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new TzpEntity(null, "123", null, BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TzpEntity(null, "123", "", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TzpEntity(null, "123", "1", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TzpEntity(null, "123", "0123456789012345", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new TzpEntity(null, "123", "123", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, null), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TzpEntity(null, "123", "123", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TzpEntity(null, "123", "123", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, "1"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TzpEntity(null, "123", "123", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567891"), USER_ID), ConstraintViolationException.class);

    }
}
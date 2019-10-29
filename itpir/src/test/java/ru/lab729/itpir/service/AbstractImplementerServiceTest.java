package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.ImplementerEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.math.BigInteger;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.ImplementerTestData.*;
import static ru.lab729.itpir.StatusImplementerTestData.STATUS_IMPLEMENTER1;
import static ru.lab729.itpir.TypeImplementerTestData.TYPE_IMPLEMENTER1;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractImplementerServiceTest extends AbstractServiceTest {

    @Autowired
    protected ImplementerService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), IMPLEMENTERS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_IMPLEMENTERS);
    }

    @Test
    void get() {
        ImplementerEntity actual = service.get(IMPLEMENTER1_ID, USER_ID);
        assertMatch(actual, IMPLEMENTER1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(IMPLEMENTER1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        ImplementerEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), IMPLEMENTER1, IMPLEMENTER2, created);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new ImplementerEntity(null, "Ершов С. А.", "8-966-100-31-24", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        ImplementerEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(IMPLEMENTER1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        ImplementerEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(IMPLEMENTER1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(IMPLEMENTER1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(IMPLEMENTER1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(IMPLEMENTER1_ID, USER_ID);
        assertMatch(service.getAll(), ADMIN_IMPLEMENTER3, IMPLEMENTER2);
    }

    @Test
    void delete() {
        service.delete(IMPLEMENTER1_ID);
        assertMatch(service.getAll(), ADMIN_IMPLEMENTER3, IMPLEMENTER2);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(IMPLEMENTER1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(IMPLEMENTER1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(IMPLEMENTER1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<ImplementerEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_IMPLEMENTER3);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_IMPLEMENTER3);
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
        validateRootCause(() -> service.create(new ImplementerEntity(null, null, "8-966-100-31-24", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ImplementerEntity(null, "", "8-966-100-31-24", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ImplementerEntity(null, "Е", "8-966-100-31-24", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ImplementerEntity(null, "012345678901234567890123456789012345678901234567891", "8-966-100-31-24", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID), ConstraintViolationException.class);


        validateRootCause(() -> service.create(new ImplementerEntity(null, "Ершов С. А.", null, "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ImplementerEntity(null, "Ершов С. А.", "", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ImplementerEntity(null, "Ершов С. А.", "01234567890", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ImplementerEntity(null, "Ершов С. А.", "0123456789012", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new ImplementerEntity(null, "Ершов С. А.", "8-966-100-31-24", null, STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ImplementerEntity(null, "Ершов С. А.", "8-966-100-31-24", "", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ImplementerEntity(null, "Ершов С. А.", "8-966-100-31-24", "s.eemail.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ImplementerEntity(null, "Ершов С. А.", "8-966-100-31-24", "1@1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new ImplementerEntity(null, "Ершов С. А.", "8-966-100-31-24", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, null), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ImplementerEntity(null, "Ершов С. А.", "8-966-100-31-24", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ImplementerEntity(null, "Ершов С. А.", "8-966-100-31-24", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "А"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ImplementerEntity(null, "Ершов С. А.", "8-966-100-31-24", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901"), USER_ID), ConstraintViolationException.class);
    }
}
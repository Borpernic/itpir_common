package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.OsEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.BandTestData.BAND1;
import static ru.lab729.itpir.CuratorTestData.CURATOR1;
import static ru.lab729.itpir.InternalNumberTestData.INTERNAL_NUMBER_1;
import static ru.lab729.itpir.OsTestData.*;
import static ru.lab729.itpir.SiteTestData.SITE1;
import static ru.lab729.itpir.StatusOsTestData.STATUSOS1;
import static ru.lab729.itpir.TypeAfsTestData.TYPE_AFS1;
import static ru.lab729.itpir.TypeAmsTestData.TYPE_AMS1;
import static ru.lab729.itpir.TypeBsTestData.TYPE_BS1;
import static ru.lab729.itpir.TypeOsTestData.TYPE_OS1;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractOsServiceTest extends AbstractServiceTest {

    @Autowired
    protected OsService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), OSS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_OSS);
    }

    @Test
    void get() {
        OsEntity actual = service.get(OS1_ID, USER_ID);
        assertMatch(actual, OS1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(OS1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        OsEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), OS2, OS1, created);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new OsEntity(null, now(), SITE1, INTERNAL_NUMBER_1, CURATOR1, BAND1, TYPE_OS1, TYPE_BS1, TYPE_AMS1, TYPE_AFS1, STATUSOS1, "Duplicate"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        OsEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(OS1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        OsEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(OS1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(OS1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(OS1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(OS1_ID, USER_ID);
        assertMatch(service.getAll(), OS2, ADMIN_OS3);
    }

    @Test
    void delete() {
        service.delete(OS1_ID);
        assertMatch(service.getAll(), OS2, ADMIN_OS3);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(OS1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(OS1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(OS1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<OsEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_OS3);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_OS3);
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
        validateRootCause(() -> service.create(new OsEntity(null, now(), SITE1, INTERNAL_NUMBER_1, CURATOR1, BAND1, TYPE_OS1, TYPE_BS1, TYPE_AMS1, TYPE_AFS1, STATUSOS1, ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new OsEntity(null, now(), SITE1, INTERNAL_NUMBER_1, CURATOR1, BAND1, TYPE_OS1, TYPE_BS1, TYPE_AMS1, TYPE_AFS1, STATUSOS1, "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567891"), USER_ID), ConstraintViolationException.class);
    }
}
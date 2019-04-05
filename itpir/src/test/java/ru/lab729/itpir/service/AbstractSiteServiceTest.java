package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.SiteEntity;
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
import static ru.lab729.itpir.OperatorTestData.OPERATOR1;
import static ru.lab729.itpir.RegionTestData.REGION1;
import static ru.lab729.itpir.SiteTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractSiteServiceTest extends AbstractServiceTest {

    @Autowired
    protected SiteService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), SITES);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_SITES);
    }

    @Test
    void getWithUser() {
        SiteEntity actual = service.get(SITE1_ID, USER_ID);
        assertMatch(actual, SITE1);
    }

    @Test
    void get() {
        SiteEntity actual = service.get(SITE1_ID);
        assertMatch(actual, SITE1);
    }

    @Test
    void getWithOsWithUser() {
        SiteEntity actual = service.getWithOs(SITE1_ID, USER_ID);
        assertMatch(actual, SITE1);
        // assertMatch(actual.getOsEntities(), OS1,OS2);
    }

    @Test
    void getWithOs() {
        SiteEntity actual = service.getWithOs(SITE1_ID, USER_ID);
        assertMatch(actual, SITE1);
        // assertMatch(actual.getOsEntities(), OS1,OS2);
    }

    @Test
    void getAllWithOsUser() {
        assertMatch(service.getAllWithOs(USER_ID), SITES);
    }

    @Test
    void getAllWithOs() {
        assertMatch(service.getAllWithOs(), ALL_SITES);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(SITE1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        SiteEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), created, SITE1, SITE2);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new SiteEntity(null, "12790", "Будапешт", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "28С", "Duplicate"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        SiteEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(SITE1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        SiteEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(SITE1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(SITE1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(SITE1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(SITE1_ID, USER_ID);
        assertMatch(service.getAll(), ADMIN_SITE4, ADMIN_SITE5, SITE2, ADMIN_SITE3);
    }

    @Test
    void delete() {
        service.delete(SITE1_ID);
        assertMatch(service.getAll(), ADMIN_SITE4, ADMIN_SITE5, SITE2, ADMIN_SITE3);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(SITE1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(SITE1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(SITE1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<SiteEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_SITE4, ADMIN_SITE5, ADMIN_SITE3);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_SITE4, ADMIN_SITE5, ADMIN_SITE3);
    }

    @Test
    void deleteAllByOperator() {
        service.deleteAllByOperator(100010);
        List<SiteEntity> all = service.getAll();
        assertMatch(all, ADMIN_SITE4, ADMIN_SITE5);

    }

    @Test
    void deleteAllByOperatorWithUser() {
        service.deleteAllByOperator(100010, USER_ID);
        List<SiteEntity> all = service.getAll();
        assertMatch(all, ADMIN_SITE4, ADMIN_SITE5);

    }

    @Test
    void deleteAllByRegion() {
        service.deleteAllByRegion(100013);
        List<SiteEntity> all = service.getAll();
        assertMatch(all, ADMIN_SITE5);

    }

    @Test
    void deleteAllByRegionWithUser() {
        service.deleteAllByRegion(100013, USER_ID);
        List<SiteEntity> all = service.getAll();
        assertMatch(all, ADMIN_SITE5);

    }

    /*


    void deleteAllByComments(String comments, int userId) throws NotFoundException;

    void deleteAllByComments(String comments) throws NotFoundException;*/



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
        validateRootCause(() -> service.create(new SiteEntity(null, "123456", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new SiteEntity(null, null, "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new SiteEntity(null, "", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new SiteEntity(null, "12345", null, OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new SiteEntity(null, "123456", "", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "123456789012345678901234567890123456789012345678901", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", null, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, null,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "", "Авиамоторная", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                null, "Авиамоторная", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "123456789012345678901", "Авиамоторная", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", null, "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "123456789012345678901234567890123456789012345678901", "28С", " БС 1279"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "", " БС 1279"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", null, " БС 1279"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "123456", " БС 1279"), USER_ID), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "28С", ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "28С", null), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new SiteEntity(null, "12345", "ааааа", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0),
                "Пекин", "Авиамоторная", "28С", "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901"), USER_ID), ConstraintViolationException.class);
    }

}
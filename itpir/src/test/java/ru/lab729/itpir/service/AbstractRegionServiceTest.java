package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.RegionEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.RegionTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractRegionServiceTest extends AbstractServiceTest {

    @Autowired
    protected RegionService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), REGIONS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_REGIONS);
    }

    @Test
    void get() {
        RegionEntity actual = service.get(REGION1_ID, USER_ID);
        assertMatch(actual, REGION1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(REGION1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        RegionEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), REGION2, REGION1, created);
    }


    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new RegionEntity(null, "77", "Duplicate"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        RegionEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(REGION1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        RegionEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(REGION1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(REGION1_ID)))
        );
    }
    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(REGION1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(REGION1_ID, USER_ID);
        assertMatch(service.getAll(), REGION2, ADMIN_REGION3);
    }

    @Test
    void delete() {
        service.delete(REGION1_ID);
        assertMatch(service.getAll(), REGION2, ADMIN_REGION3);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(REGION1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(REGION1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(REGION1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<RegionEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_REGION3);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_REGION3);
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
        validateRootCause(() -> service.create(new RegionEntity(null, "", "Comments"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new RegionEntity(null, "Р", "Comments"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new RegionEntity(null, null, "Comments"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new RegionEntity(null, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444", "Comments"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new RegionEntity(null, "Регион", ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new RegionEntity(null, "Регион", "C"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new RegionEntity(null, "Регион", null), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new RegionEntity(null, "Регион", "Comments77777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777"), USER_ID), ConstraintViolationException.class);
    }
}
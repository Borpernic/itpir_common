package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.DepartmentEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.DepartmentTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractDepartmentServiceTest extends AbstractServiceTest {

    @Autowired
    protected DepartmentService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), DEPARTMENTS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_DEPARTMENTS);
    }

    @Test
    void get() {
        DepartmentEntity actual = service.get(DEPARTMENT1_ID, USER_ID);
        assertMatch(actual, DEPARTMENT1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(DEPARTMENT1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        DepartmentEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), DEPARTMENT2, DEPARTMENT1, created);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new DepartmentEntity(null, "Проектировщик", "Duplicate"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        DepartmentEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(DEPARTMENT1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        DepartmentEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(DEPARTMENT1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(DEPARTMENT1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(DEPARTMENT1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(DEPARTMENT1_ID, USER_ID);
        assertMatch(service.getAll(), ADMIN_DEPARTMENT3, ADMIN_DEPARTMENT4,
                ADMIN_DEPARTMENT7, DEPARTMENT2, ADMIN_DEPARTMENT12, ADMIN_DEPARTMENT11, ADMIN_DEPARTMENT5,
                ADMIN_DEPARTMENT10, ADMIN_DEPARTMENT8, ADMIN_DEPARTMENT6, ADMIN_DEPARTMENT9);
    }

    @Test
    void delete() {
        service.delete(DEPARTMENT1_ID);
        assertMatch(service.getAll(), ADMIN_DEPARTMENT3, ADMIN_DEPARTMENT4,
                ADMIN_DEPARTMENT7, DEPARTMENT2, ADMIN_DEPARTMENT12, ADMIN_DEPARTMENT11, ADMIN_DEPARTMENT5,
                ADMIN_DEPARTMENT10, ADMIN_DEPARTMENT8, ADMIN_DEPARTMENT6, ADMIN_DEPARTMENT9);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(DEPARTMENT1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(DEPARTMENT1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(DEPARTMENT1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<DepartmentEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_DEPARTMENT3, ADMIN_DEPARTMENT4,
                ADMIN_DEPARTMENT7, ADMIN_DEPARTMENT12, ADMIN_DEPARTMENT11, ADMIN_DEPARTMENT5,
                ADMIN_DEPARTMENT10, ADMIN_DEPARTMENT8, ADMIN_DEPARTMENT6, ADMIN_DEPARTMENT9);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_DEPARTMENT3, ADMIN_DEPARTMENT4,
                ADMIN_DEPARTMENT7, ADMIN_DEPARTMENT12, ADMIN_DEPARTMENT11, ADMIN_DEPARTMENT5,
                ADMIN_DEPARTMENT10, ADMIN_DEPARTMENT8, ADMIN_DEPARTMENT6, ADMIN_DEPARTMENT9);
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
        validateRootCause(() -> service.create(new DepartmentEntity(null, "", "Подразделение"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new DepartmentEntity(null, "С", "Подразделение"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new DepartmentEntity(null, null, "Подразделение"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new DepartmentEntity(null, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444", "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new DepartmentEntity(null, "Проектировщик+", ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new DepartmentEntity(null, "Проектировщик+", "C"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new DepartmentEntity(null, "Проектировщик+", "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444"), USER_ID), ConstraintViolationException.class);
    }
}
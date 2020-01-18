package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lab729.itpir.model.TaskEntity;
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
import static ru.lab729.itpir.DepartmentTestData.DEPARTMENT1;
import static ru.lab729.itpir.ResultTaskTestData.RESULT_TASK1;
import static ru.lab729.itpir.TaskTestData.*;
import static ru.lab729.itpir.TypeTaskTestData.TYPE_TASK1;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractTaskServiceTest extends AbstractServiceTest {

    @Autowired
    protected TaskService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), TASK1, TASK2);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_TASKS);
    }

    @Test
    void get() {
        TaskEntity actual = service.get(TASK1_ID, USER_ID);
        assertMatch(actual, TASK1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(TASK1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        TaskEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), TASK1, TASK2, created);
    }

/*    @Test   непроверять
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new TaskEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
                TYPE_TASK1, DEPARTMENT1, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
                RESULT_TASK1, "Задача 1"), USER_ID));
    }*/

    @Test
    void updateWithUserId() {
        TaskEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(TASK1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        TaskEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(TASK1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(TASK1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(TASK1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(TASK1_ID, USER_ID);
        assertMatch(service.getAll(), TASK2, ADMIN_TASK3, ADMIN_TASK4);
    }

    @Test
    void delete() {
        service.delete(TASK1_ID);
        assertMatch(service.getAll(), TASK2, ADMIN_TASK3, ADMIN_TASK4);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(TASK1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(TASK1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(TASK1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<TaskEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_TASK3, ADMIN_TASK4);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_TASK3, ADMIN_TASK4);
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
        validateRootCause(() -> service.create(new TaskEntity(null, null, of(2019, Month.MAY, 30, 10, 0),
                TYPE_TASK1, DEPARTMENT1, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
                RESULT_TASK1, "Задача 1"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TaskEntity(null, ACTIVITY1, null,
                TYPE_TASK1, DEPARTMENT1, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
                RESULT_TASK1, "Задача 1"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TaskEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
                null, DEPARTMENT1, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
                RESULT_TASK1, "Задача 1"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TaskEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
                TYPE_TASK1, null, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
                RESULT_TASK1, "Задача 1"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TaskEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
                TYPE_TASK1, DEPARTMENT1, null, true, true, null,
                RESULT_TASK1, "Задача 1"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TaskEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
                TYPE_TASK1, DEPARTMENT1, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
                null, "Задача 1"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TaskEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
                TYPE_TASK1, DEPARTMENT1, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
                RESULT_TASK1, null), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TaskEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
                TYPE_TASK1, DEPARTMENT1, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
                RESULT_TASK1, ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TaskEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
                TYPE_TASK1, DEPARTMENT1, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
                RESULT_TASK1, "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567891"), USER_ID), ConstraintViolationException.class);


    }
}
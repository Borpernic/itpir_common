package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.ResultTaskEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.ResultTaskTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractResultTaskServiceTest extends AbstractServiceTest {

    @Autowired
    protected ResultTaskService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), RESULT_TASKS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_RESULT_TASKS);
    }

    @Test
    void get() {
        ResultTaskEntity actual = service.get(RESULT_TASK1_ID, USER_ID);
        assertMatch(actual, RESULT_TASK1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(RESULT_TASK1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        ResultTaskEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), RESULT_TASK1, RESULT_TASK2, created);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new ResultTaskEntity(null, "Оплачено", "Duplicate"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        ResultTaskEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(RESULT_TASK1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        ResultTaskEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(RESULT_TASK1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(RESULT_TASK1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(RESULT_TASK1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(RESULT_TASK1_ID, USER_ID);
        assertMatch(service.getAll(), RESULT_TASK2,
                ADMIN_RESULT_TASK3, ADMIN_RESULT_TASK4, ADMIN_RESULT_TASK5, ADMIN_RESULT_TASK6, ADMIN_RESULT_TASK7, ADMIN_RESULT_TASK8, ADMIN_RESULT_TASK9,
                ADMIN_RESULT_TASK10, ADMIN_RESULT_TASK11, ADMIN_RESULT_TASK12, ADMIN_RESULT_TASK13, ADMIN_RESULT_TASK14,
                ADMIN_RESULT_TASK15, ADMIN_RESULT_TASK16, ADMIN_RESULT_TASK17, ADMIN_RESULT_TASK18, ADMIN_RESULT_TASK19, ADMIN_RESULT_TASK20, ADMIN_RESULT_TASK21);
    }

    @Test
    void delete() {
        service.delete(RESULT_TASK1_ID);
        assertMatch(service.getAll(), RESULT_TASK2,
                ADMIN_RESULT_TASK3, ADMIN_RESULT_TASK4, ADMIN_RESULT_TASK5, ADMIN_RESULT_TASK6, ADMIN_RESULT_TASK7, ADMIN_RESULT_TASK8, ADMIN_RESULT_TASK9,
                ADMIN_RESULT_TASK10, ADMIN_RESULT_TASK11, ADMIN_RESULT_TASK12, ADMIN_RESULT_TASK13, ADMIN_RESULT_TASK14,
                ADMIN_RESULT_TASK15, ADMIN_RESULT_TASK16, ADMIN_RESULT_TASK17, ADMIN_RESULT_TASK18, ADMIN_RESULT_TASK19, ADMIN_RESULT_TASK20, ADMIN_RESULT_TASK21);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(RESULT_TASK1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(RESULT_TASK1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(RESULT_TASK1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<ResultTaskEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_RESULT_TASK3, ADMIN_RESULT_TASK4, ADMIN_RESULT_TASK5, ADMIN_RESULT_TASK6, ADMIN_RESULT_TASK7, ADMIN_RESULT_TASK8, ADMIN_RESULT_TASK9,
                ADMIN_RESULT_TASK10, ADMIN_RESULT_TASK11, ADMIN_RESULT_TASK12, ADMIN_RESULT_TASK13, ADMIN_RESULT_TASK14,
                ADMIN_RESULT_TASK15, ADMIN_RESULT_TASK16, ADMIN_RESULT_TASK17, ADMIN_RESULT_TASK18, ADMIN_RESULT_TASK19, ADMIN_RESULT_TASK20, ADMIN_RESULT_TASK21);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_RESULT_TASK3, ADMIN_RESULT_TASK4, ADMIN_RESULT_TASK5, ADMIN_RESULT_TASK6, ADMIN_RESULT_TASK7, ADMIN_RESULT_TASK8, ADMIN_RESULT_TASK9,
                ADMIN_RESULT_TASK10, ADMIN_RESULT_TASK11, ADMIN_RESULT_TASK12, ADMIN_RESULT_TASK13, ADMIN_RESULT_TASK14,
                ADMIN_RESULT_TASK15, ADMIN_RESULT_TASK16, ADMIN_RESULT_TASK17, ADMIN_RESULT_TASK18, ADMIN_RESULT_TASK19, ADMIN_RESULT_TASK20, ADMIN_RESULT_TASK21);
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
        validateRootCause(() -> service.create(new ResultTaskEntity(null, "", "Результат задачи"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ResultTaskEntity(null, "С", "Результат задачи"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ResultTaskEntity(null, null, "Результат задачи"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ResultTaskEntity(null, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444", "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ResultTaskEntity(null, "Оплачено+", ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ResultTaskEntity(null, "Оплачено+", "C"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ResultTaskEntity(null, "Оплачено+", "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444"), USER_ID), ConstraintViolationException.class);
    }
}
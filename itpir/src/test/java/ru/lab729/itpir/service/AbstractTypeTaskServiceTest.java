package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.TypeTaskEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.TypeTaskTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractTypeTaskServiceTest extends AbstractServiceTest {

    @Autowired
    protected TypeTaskService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), TYPE_TASKS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_TYPE_TASKS);
    }

    @Test
    void get() {
        TypeTaskEntity actual = service.get(TYPE_TASK1_ID, USER_ID);
        assertMatch(actual, TYPE_TASK1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(TYPE_TASK1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        TypeTaskEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), TYPE_TASK1, TYPE_TASK2, TYPE_TASK3, created);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new TypeTaskEntity(null, "Получен заказ от PM", "Duplicate"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        TypeTaskEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(TYPE_TASK1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        TypeTaskEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(TYPE_TASK1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(TYPE_TASK1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(TYPE_TASK1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(TYPE_TASK1_ID, USER_ID);
        assertMatch(service.getAll(), TYPE_TASK2, TYPE_TASK3,
                ADMIN_TYPE_TASK4, ADMIN_TYPE_TASK5, ADMIN_TYPE_TASK6, ADMIN_TYPE_TASK7, ADMIN_TYPE_TASK8, ADMIN_TYPE_TASK9,
                ADMIN_TYPE_TASK10, ADMIN_TYPE_TASK11, ADMIN_TYPE_TASK12, ADMIN_TYPE_TASK13, ADMIN_TYPE_TASK14,
                ADMIN_TYPE_TASK15, ADMIN_TYPE_TASK16, ADMIN_TYPE_TASK17, ADMIN_TYPE_TASK18, ADMIN_TYPE_TASK19, ADMIN_TYPE_TASK20);
    }

    @Test
    void delete() {
        service.delete(TYPE_TASK1_ID);
        assertMatch(service.getAll(), TYPE_TASK2, TYPE_TASK3,
                ADMIN_TYPE_TASK4, ADMIN_TYPE_TASK5, ADMIN_TYPE_TASK6, ADMIN_TYPE_TASK7, ADMIN_TYPE_TASK8, ADMIN_TYPE_TASK9,
                ADMIN_TYPE_TASK10, ADMIN_TYPE_TASK11, ADMIN_TYPE_TASK12, ADMIN_TYPE_TASK13, ADMIN_TYPE_TASK14,
                ADMIN_TYPE_TASK15, ADMIN_TYPE_TASK16, ADMIN_TYPE_TASK17, ADMIN_TYPE_TASK18, ADMIN_TYPE_TASK19, ADMIN_TYPE_TASK20);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(TYPE_TASK1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(TYPE_TASK1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(TYPE_TASK1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<TypeTaskEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_TYPE_TASK4, ADMIN_TYPE_TASK5, ADMIN_TYPE_TASK6, ADMIN_TYPE_TASK7, ADMIN_TYPE_TASK8, ADMIN_TYPE_TASK9,
                ADMIN_TYPE_TASK10, ADMIN_TYPE_TASK11, ADMIN_TYPE_TASK12, ADMIN_TYPE_TASK13, ADMIN_TYPE_TASK14,
                ADMIN_TYPE_TASK15, ADMIN_TYPE_TASK16, ADMIN_TYPE_TASK17, ADMIN_TYPE_TASK18, ADMIN_TYPE_TASK19, ADMIN_TYPE_TASK20);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_TYPE_TASK4, ADMIN_TYPE_TASK5, ADMIN_TYPE_TASK6, ADMIN_TYPE_TASK7, ADMIN_TYPE_TASK8, ADMIN_TYPE_TASK9,
                ADMIN_TYPE_TASK10, ADMIN_TYPE_TASK11, ADMIN_TYPE_TASK12, ADMIN_TYPE_TASK13, ADMIN_TYPE_TASK14,
                ADMIN_TYPE_TASK15, ADMIN_TYPE_TASK16, ADMIN_TYPE_TASK17, ADMIN_TYPE_TASK18, ADMIN_TYPE_TASK19, ADMIN_TYPE_TASK20);
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
        validateRootCause(() -> service.create(new TypeTaskEntity(null, "", "Тип задачи"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeTaskEntity(null, "С", "Тип задачи"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeTaskEntity(null, null, "Тип задачи"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeTaskEntity(null, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444", "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeTaskEntity(null, "Отправка+", ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeTaskEntity(null, "Отправка+", "C"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new TypeTaskEntity(null, "Отправка+", "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444"), USER_ID), ConstraintViolationException.class);
    }
}
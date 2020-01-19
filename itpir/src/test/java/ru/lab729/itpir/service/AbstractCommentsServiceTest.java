package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lab729.itpir.model.CommentsEntity;
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
import static ru.lab729.itpir.CommentsTestData.*;
import static ru.lab729.itpir.ImplementerTestData.IMPLEMENTER1;
import static ru.lab729.itpir.OsTestData.OS1;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractCommentsServiceTest extends AbstractServiceTest {

    @Autowired
    protected CommentsService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), COMMENTS1, COMMENTS2);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_COMMENTSS);
    }

    @Test
    void get() {
        CommentsEntity actual = service.get(COMMENTS1_ID, USER_ID);
        assertMatch(actual, COMMENTS1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(COMMENTS1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        CommentsEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), COMMENTS1, COMMENTS2, created);
    }

/*    @Test   непроверять
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new CommentsEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
                TYPE_COMMENTS1, DEPARTMENT1, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
                RESULT_COMMENTS1, "Задача 1"), USER_ID));
    }*/

    @Test
    void updateWithUserId() {
        CommentsEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(COMMENTS1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        CommentsEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(COMMENTS1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(COMMENTS1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(COMMENTS1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(COMMENTS1_ID, USER_ID);
        assertMatch(service.getAll(), COMMENTS2, ADMIN_COMMENTS3);
    }

    @Test
    void delete() {
        service.delete(COMMENTS1_ID);
        assertMatch(service.getAll(), COMMENTS2, ADMIN_COMMENTS3);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(COMMENTS1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(COMMENTS1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(COMMENTS1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<CommentsEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_COMMENTS3);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_COMMENTS3);
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
        validateRootCause(() -> service.create(new CommentsEntity(null, null, IMPLEMENTER1,
                of(2019, Month.MAY, 30, 10, 0), "Комментарий"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new CommentsEntity(null, OS1, null,
                of(2019, Month.MAY, 30, 10, 0), "Комментарий"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new CommentsEntity(null, OS1, IMPLEMENTER1,
                null, "Комментарий"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new CommentsEntity(null, OS1, IMPLEMENTER1,
                of(2019, Month.MAY, 30, 10, 0), null), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new CommentsEntity(null, OS1, IMPLEMENTER1,
                of(2019, Month.MAY, 30, 10, 0), ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new CommentsEntity(null, OS1, IMPLEMENTER1,
                of(2019, Month.MAY, 30, 10, 0),
                "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567891"), USER_ID), ConstraintViolationException.class);
        ;
    }
}
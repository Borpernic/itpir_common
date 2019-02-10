package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import static ru.lab729.itpir.OperatorTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractOperatorServiceTest extends AbstractServiceTest {

    @Autowired
    protected OperatorService service;

    @Test
    void delete() {
        service.delete(OPERATOR1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), OPERATOR1, OPERATOR2, ADMIN_OPERATOR3);
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(OPERATOR1_ID, 1));
    }

    @Test
    void create() {
        OperatorEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), created, OPERATOR1, OPERATOR2, ADMIN_OPERATOR3);
    }

    @Test
    void get() {
        OperatorEntity actual = service.get(OPERATOR1_ID, ADMIN_ID);
        assertMatch(actual, OPERATOR1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(OPERATOR1_ID, ADMIN_ID));
    }

    @Test
    void update() {
        OperatorEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(OPERATOR1_ID, USER_ID), updated);
    }

    @Test
    void updateNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(OPERATOR1, ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(OPERATOR1_ID)))
        );
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(USER_ID), OPERATORS);
    }

    /*@Test
    void getBetween() {
        assertMatch(service.getBetweenDates(
                LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID), MEAL3, MEAL2, MEAL1);
    }*/

   /* @Test
    void testValidation() {
        assumeTrue(isJpaBased());
        validateRootCause(() -> service.create(new OperatorEntity(null,"bn","bn"), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new OperatorEntity(null,"bn","bn"), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new OperatorEntity(null,"bn","bn"), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new OperatorEntity(null,"bn","bn"), ConstraintViolationException.class);
    }*/
}
package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.CustomerEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.CustomerTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractCustomerServiceTest extends AbstractServiceTest {

    @Autowired
    protected CustomerService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), CUSTOMERS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_CUSTOMERS);
    }

    @Test
    void get() {
        CustomerEntity actual = service.get(CUSTOMER1_ID, USER_ID);
        assertMatch(actual, CUSTOMER1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(CUSTOMER1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        CustomerEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), CUSTOMER2, CUSTOMER3, CUSTOMER1, created, CUSTOMER4);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new CustomerEntity(null, "Эрикcсон", "Duplicate"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        CustomerEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(CUSTOMER1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        CustomerEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(CUSTOMER1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(CUSTOMER1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(CUSTOMER1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(CUSTOMER1_ID, USER_ID);
        assertMatch(service.getAll(), CUSTOMER2, CUSTOMER3, ADMIN_CUSTOMER7, CUSTOMER4, ADMIN_CUSTOMER5, ADMIN_CUSTOMER6);
    }

    @Test
    void delete() {
        service.delete(CUSTOMER1_ID);
        assertMatch(service.getAll(), CUSTOMER2, CUSTOMER3, ADMIN_CUSTOMER7, CUSTOMER4, ADMIN_CUSTOMER5, ADMIN_CUSTOMER6);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(CUSTOMER1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(CUSTOMER1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(CUSTOMER1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<CustomerEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_CUSTOMER7, ADMIN_CUSTOMER5, ADMIN_CUSTOMER6);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_CUSTOMER7, ADMIN_CUSTOMER5, ADMIN_CUSTOMER6);
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
        validateRootCause(() -> service.create(new CustomerEntity(null, "", "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new CustomerEntity(null, "С", "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new CustomerEntity(null, null, "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new CustomerEntity(null, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444", "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new CustomerEntity(null, "Сидоров", ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new CustomerEntity(null, "Сидоров", "C"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new CustomerEntity(null, "Сидоров", "Co"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new CustomerEntity(null, "Сидоров", "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444"), USER_ID), ConstraintViolationException.class);
    }
}
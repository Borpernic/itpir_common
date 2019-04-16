package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.ContactsAdEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.ContactsAdTestData.*;
import static ru.lab729.itpir.SiteTestData.SITE1;
import static ru.lab729.itpir.SiteTestData.SITE1_ID;
import static ru.lab729.itpir.StatusContactTestData.STATUSCONTACT1;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractContactsAdServiceTest extends AbstractServiceTest {

    @Autowired
    protected ContactsAdService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), CONTACTS_ADS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_CONTACTS_ADS);
    }


    @Test
    void getAllByText() {
        assertMatch(service.getAllByText(TEXT_FOR_SERCH), CONTACTS_AD2, ADMIN_CONTACTS_AD3);
    }

    @Test
    void getAllByTextWithUser() {
        assertMatch(service.getAllByText(TEXT_FOR_SERCH, USER_ID), CONTACTS_AD2);
    }


    @Test
    void getWithUserId() {
        ContactsAdEntity actual = service.get(CONTACTS_AD1_ID, USER_ID);
        assertMatch(actual, CONTACTS_AD1);
    }

    @Test
    void get() {
        ContactsAdEntity actual = service.get(CONTACTS_AD1_ID);
        assertMatch(actual, CONTACTS_AD1);
    }


    @Test
    void getNotFound() {
        service.get(CONTACTS_AD1_ID);
        assertThrows(NotFoundException.class, () -> service.get(CONTACTS_AD1_ID));
    }

    @Test
    void getWithUserIdNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(CONTACTS_AD1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        ContactsAdEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), CONTACTS_AD2, CONTACTS_AD1, created);
    }

    @Test
    void createDuplicate() {

        assertThrows(DataAccessException.class, () -> service.create(new ContactsAdEntity(null, SITE1,
                "Лавров", "Дмитрий", "+7(926)637-22-19", "bn1@mail.ru",
                STATUSCONTACT1, false), USER_ID));
    }

    @Test
    void updateWithUserId() {
        ContactsAdEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(CONTACTS_AD1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        ContactsAdEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(CONTACTS_AD1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(CONTACTS_AD1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(CONTACTS_AD1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(CONTACTS_AD1_ID, USER_ID);
        assertMatch(service.getAll(), CONTACTS_AD2, ADMIN_CONTACTS_AD3);
    }

    @Test
    void delete() {
        service.delete(CONTACTS_AD1_ID);
        assertMatch(service.getAll(), CONTACTS_AD2, ADMIN_CONTACTS_AD3);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(CONTACTS_AD1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(CONTACTS_AD1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(CONTACTS_AD1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<ContactsAdEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_CONTACTS_AD3);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_CONTACTS_AD3);
    }


    @Test
    void deleteAllBySiteWithUserNotFound() {
        service.deleteAllBySite(SITE1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAllBySite(SITE1_ID, USER_ID));
        assertMatch(service.getAll(), ADMIN_CONTACTS_AD3);
    }

    @Test
    void deleteAllBySiteWithUser() {
        service.deleteAllBySite(SITE1_ID, USER_ID);
        assertMatch(service.getAll(), ADMIN_CONTACTS_AD3);
    }


    @Test
    void deleteAllBySiteNotFound() {
        service.deleteAllBySite(SITE1_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAllBySite(SITE1_ID));
        assertMatch(service.getAll(), ADMIN_CONTACTS_AD3);
    }

    @Test
    void deleteAllBySite() {
        service.deleteAllBySite(SITE1_ID, USER_ID);
        assertMatch(service.getAll(), ADMIN_CONTACTS_AD3);
    }

    @Test
    void deleteAllByTextWithUserNotFound() {
        service.deleteAllByText(TEXT_FOR_SERCH, USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAllByText(TEXT_FOR_SERCH, USER_ID));
        assertMatch(service.getAll(), CONTACTS_AD2);
    }

    @Test
    void deleteAllByTextWithUser() {
        service.deleteAllByText(TEXT_FOR_SERCH, USER_ID);
        assertMatch(service.getAll(), CONTACTS_AD2);
    }


    @Test
    void deleteAllByTextNotFound() {
        service.deleteAllByText(TEXT_FOR_SERCH);
        assertThrows(NotFoundException.class, () -> service.deleteAllByText(TEXT_FOR_SERCH));
        assertMatch(service.getAll(), CONTACTS_AD2, ADMIN_CONTACTS_AD3);
    }

    @Test
    void deleteAllByText() {
        service.deleteAllByText(TEXT_FOR_SERCH);
        assertMatch(service.getAll(), CONTACTS_AD2, ADMIN_CONTACTS_AD3);
    }
/*

   @Test
    void getBetween() {
        assertMatch(service.getBetweenDates(
                LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID), MEAL3, MEAL2, MEAL1);
    }
*/


/*    @Test
    void testValidation() {
        assumeTrue(isJpaBased());
        validateRootCause(() -> service.create(new ContactsAdEntity(null, "", "Comments"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ContactsAdEntity(null, "О", "Comments"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ContactsAdEntity(null, null, "Comments"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ContactsAdEntity(null, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444", "Comments"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ContactsAdEntity(null, "Оператор", ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ContactsAdEntity(null, "Оператор", "C"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ContactsAdEntity(null, "Оператор", null), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new ContactsAdEntity(null, "Оператор", "Comments77777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777"), USER_ID), ConstraintViolationException.class);
    }*/
}
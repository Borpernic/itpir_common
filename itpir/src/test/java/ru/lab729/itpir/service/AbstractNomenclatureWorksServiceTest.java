package ru.lab729.itpir.service;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.lab729.itpir.model.NomenclatureWorksEntity;
import ru.lab729.itpir.util.exception.ErrorType;
import ru.lab729.itpir.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.lab729.itpir.NomenclatureWorksTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;
import static ru.lab729.itpir.UserTestData.USER_ID;

public abstract class AbstractNomenclatureWorksServiceTest extends AbstractServiceTest {

    @Autowired
    protected NomenclatureWorksService service;

    @Test
    void getAllWithUser() {
        assertMatch(service.getAll(USER_ID), NOMENCLATURE_WORKSS);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), ALL_NOMENCLATURE_WORKSS);
    }

    @Test
    void get() {
        NomenclatureWorksEntity actual = service.get(NOMENCLATURE_WORKS1_ID, USER_ID);
        assertMatch(actual, NOMENCLATURE_WORKS1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOMENCLATURE_WORKS1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        NomenclatureWorksEntity created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), NOMENCLATURE_WORKS1, NOMENCLATURE_WORKS2, NOMENCLATURE_WORKS3, created);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataAccessException.class, () -> service.create(new NomenclatureWorksEntity(null, "Обследование ОС", "Duplicate"), USER_ID));
    }

    @Test
    void updateWithUserId() {
        NomenclatureWorksEntity updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(NOMENCLATURE_WORKS1_ID, USER_ID), updated);
    }

    @Test
    void update() {
        NomenclatureWorksEntity updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(NOMENCLATURE_WORKS1_ID), updated);
    }

    @Test
    void updateWithUserIdNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(NOMENCLATURE_WORKS1_ID)))
        );
    }

    @Test
    void updateNotFound() {
        service.delete(getUpdated().getId());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.update(getUpdated()));

        assertAll(
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(ErrorType.DATA_NOT_FOUND.name())),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(NotFoundException.NOT_FOUND_EXCEPTION)),
                () -> MatcherAssert.assertThat(thrown.getMessage(), containsString(String.valueOf(NOMENCLATURE_WORKS1_ID)))
        );
    }

    @Test
    void deleteWithUserId() {
        service.delete(NOMENCLATURE_WORKS1_ID, USER_ID);
        assertMatch(service.getAll(), NOMENCLATURE_WORKS2, NOMENCLATURE_WORKS3, ADMIN_NOMENCLATURE_WORKS4, ADMIN_NOMENCLATURE_WORKS5, ADMIN_NOMENCLATURE_WORKS6, ADMIN_NOMENCLATURE_WORKS7, ADMIN_NOMENCLATURE_WORKS8, ADMIN_NOMENCLATURE_WORKS9, ADMIN_NOMENCLATURE_WORKS10, ADMIN_NOMENCLATURE_WORKS11, ADMIN_NOMENCLATURE_WORKS12, ADMIN_NOMENCLATURE_WORKS13, ADMIN_NOMENCLATURE_WORKS14);
    }

    @Test
    void delete() {
        service.delete(NOMENCLATURE_WORKS1_ID);
        assertMatch(service.getAll(), NOMENCLATURE_WORKS2, NOMENCLATURE_WORKS3, ADMIN_NOMENCLATURE_WORKS4, ADMIN_NOMENCLATURE_WORKS5, ADMIN_NOMENCLATURE_WORKS6, ADMIN_NOMENCLATURE_WORKS7, ADMIN_NOMENCLATURE_WORKS8, ADMIN_NOMENCLATURE_WORKS9, ADMIN_NOMENCLATURE_WORKS10, ADMIN_NOMENCLATURE_WORKS11, ADMIN_NOMENCLATURE_WORKS12, ADMIN_NOMENCLATURE_WORKS13, ADMIN_NOMENCLATURE_WORKS14);
    }

    @Test
    void deleteNotFoundWithUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(NOMENCLATURE_WORKS1_ID, ADMIN_ID));
    }

    @Test
    void deleteNotFound() {
        service.delete(NOMENCLATURE_WORKS1_ID);
        assertThrows(NotFoundException.class, () -> service.delete(NOMENCLATURE_WORKS1_ID));
    }

    @Test
    void deleteAllNotFound() {
        service.deleteAll();
        assertThrows(NotFoundException.class, () -> service.deleteAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        List<NomenclatureWorksEntity> all = service.getAll();
        assertMatch(all, 0);

    }

    @Test
    void deleteAllWithUserNotFound() {
        service.deleteAll(USER_ID);
        assertThrows(NotFoundException.class, () -> service.deleteAll(USER_ID));
        assertMatch(service.getAll(), ADMIN_NOMENCLATURE_WORKS4, ADMIN_NOMENCLATURE_WORKS5, ADMIN_NOMENCLATURE_WORKS6, ADMIN_NOMENCLATURE_WORKS7, ADMIN_NOMENCLATURE_WORKS8, ADMIN_NOMENCLATURE_WORKS9, ADMIN_NOMENCLATURE_WORKS10, ADMIN_NOMENCLATURE_WORKS11, ADMIN_NOMENCLATURE_WORKS12, ADMIN_NOMENCLATURE_WORKS13, ADMIN_NOMENCLATURE_WORKS14);
    }

    @Test
    void deleteAllWithUser() {
        service.deleteAll(USER_ID);
        assertMatch(service.getAll(), ADMIN_NOMENCLATURE_WORKS4, ADMIN_NOMENCLATURE_WORKS5, ADMIN_NOMENCLATURE_WORKS6, ADMIN_NOMENCLATURE_WORKS7, ADMIN_NOMENCLATURE_WORKS8, ADMIN_NOMENCLATURE_WORKS9, ADMIN_NOMENCLATURE_WORKS10, ADMIN_NOMENCLATURE_WORKS11, ADMIN_NOMENCLATURE_WORKS12, ADMIN_NOMENCLATURE_WORKS13, ADMIN_NOMENCLATURE_WORKS14);
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
        validateRootCause(() -> service.create(new NomenclatureWorksEntity(null, "", "ПД/РД"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new NomenclatureWorksEntity(null, "С", "ПД/РД"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new NomenclatureWorksEntity(null, null, "ПД/РД"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new NomenclatureWorksEntity(null, "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444", "МТС"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new NomenclatureWorksEntity(null, "Обследование", ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new NomenclatureWorksEntity(null, "Обследование", "П"), USER_ID), ConstraintViolationException.class);
        //validateRootCause(() -> service.create(new NomenclatureWorksEntity(null, "Обследование", null), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new NomenclatureWorksEntity(null, "Обследование", "123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444123456789012131444444444444444444444444444444444444444444444444444444444444444444444444444444444"), USER_ID), ConstraintViolationException.class);
    }
}
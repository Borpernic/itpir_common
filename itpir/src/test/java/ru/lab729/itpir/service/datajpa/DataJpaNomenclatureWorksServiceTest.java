package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.NomenclatureWorksEntity;
import ru.lab729.itpir.service.AbstractNomenclatureWorksServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.NomenclatureWorksTestData.*;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaNomenclatureWorksServiceTest extends AbstractNomenclatureWorksServiceTest {
    @Test
    void testGetWithUser() {
        NomenclatureWorksEntity adminOperator = service.getWithUser(ADMIN_NOMENCLATURE_WORKS_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_NOMENCLATURE_WORKS4);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(NOMENCLATURE_WORKS1_ID, ADMIN_ID));

    }
}

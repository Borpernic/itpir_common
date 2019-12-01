package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.TzpEntity;
import ru.lab729.itpir.service.AbstractTzpServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.TzpTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaTzpServiceTest extends AbstractTzpServiceTest {
    @Test
    void testGetWithUser() {
        TzpEntity adminOperator = service.getWithUser(ADMIN_TZP_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_TZP23);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(TZP1_ID, ADMIN_ID));

    }
}

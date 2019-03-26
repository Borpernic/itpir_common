package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.StatusOsEntity;
import ru.lab729.itpir.service.AbstractStatusOsServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.StatusOsTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaStatusOsServiceTest extends AbstractStatusOsServiceTest {
    @Test
    void testGetWithUser() {
        StatusOsEntity adminOperator = service.getWithUser(ADMIN_STATUSOS_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_STATUSOS3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(STATUSOS1_ID, ADMIN_ID));

    }
}

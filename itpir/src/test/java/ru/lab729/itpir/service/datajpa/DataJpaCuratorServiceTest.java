package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.CuratorEntity;
import ru.lab729.itpir.service.AbstractCuratorServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.CuratorTestData.*;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaCuratorServiceTest extends AbstractCuratorServiceTest {
    @Test
    void testGetWithUser() {
        CuratorEntity adminOperator = service.getWithUser(ADMIN_CURATOR_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_CURATOR3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(CURATOR1_ID, ADMIN_ID));

    }
}

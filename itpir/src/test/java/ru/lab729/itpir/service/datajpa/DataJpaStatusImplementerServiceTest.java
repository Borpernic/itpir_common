package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.StatusImplementerEntity;
import ru.lab729.itpir.service.AbstractStatusImplementerServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.StatusImplementerTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaStatusImplementerServiceTest extends AbstractStatusImplementerServiceTest {
    @Test
    void testGetWithUser() {
        StatusImplementerEntity adminOperator = service.getWithUser(ADMIN_STATUS_IMPLEMENTER_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_STATUS_IMPLEMENTER3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(STATUS_IMPLEMENTER1_ID, ADMIN_ID));

    }
}

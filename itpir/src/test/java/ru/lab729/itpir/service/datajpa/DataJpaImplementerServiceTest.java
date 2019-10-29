package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.ImplementerEntity;
import ru.lab729.itpir.service.AbstractImplementerServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.ImplementerTestData.*;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaImplementerServiceTest extends AbstractImplementerServiceTest {
    @Test
    void testGetWithUser() {
        ImplementerEntity adminOperator = service.getWithUser(ADMIN_IMPLEMENTER_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_IMPLEMENTER3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(IMPLEMENTER1_ID, ADMIN_ID));

    }
}

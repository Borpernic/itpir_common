package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.DateChangeStatusEntity;
import ru.lab729.itpir.service.AbstractDateChangeStatusServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.DateChangeStatusTestData.*;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaDateChangeStatusServiceTest extends AbstractDateChangeStatusServiceTest {
    @Test
    void testGetWithUser() {
        DateChangeStatusEntity adminOperator = service.getWithUser(ADMIN_DATE_CHANGE_STATUS_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_DATE_CHANGE_STATUS3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(DATE_CHANGE_STATUS1_ID, ADMIN_ID));

    }
}

package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.StatusContactsEntity;
import ru.lab729.itpir.service.AbstractStatusContactServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.StatusContactTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaStatusContactServiceTest extends AbstractStatusContactServiceTest {
    @Test
    void testGetWithUser() {
        StatusContactsEntity adminOperator = service.getWithUser(ADMIN_STATUSCONTACT_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_STATUSCONTACT3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(STATUSCONTACT1_ID, ADMIN_ID));

    }
}

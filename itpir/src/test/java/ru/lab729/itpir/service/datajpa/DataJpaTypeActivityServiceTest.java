package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.TypeActivityEntity;
import ru.lab729.itpir.service.AbstractTypeActivityServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.TypeActivityTestData.*;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaTypeActivityServiceTest extends AbstractTypeActivityServiceTest {
    @Test
    void testGetWithUser() {
        TypeActivityEntity adminOperator = service.getWithUser(ADMIN_TYPE_ACTIVITY_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_TYPE_ACTIVITY3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(TYPE_ACTIVITY1_ID, ADMIN_ID));

    }
}

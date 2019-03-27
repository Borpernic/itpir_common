package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.TypeAmsEntity;
import ru.lab729.itpir.service.AbstractTypeAmsServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.TypeAmsTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaTypeAmsServiceTest extends AbstractTypeAmsServiceTest {
    @Test
    void testGetWithUser() {
        TypeAmsEntity adminOperator = service.getWithUser(ADMIN_TYPE_AMS_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_TYPE_AMS5);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(TYPE_AMS1_ID, ADMIN_ID));

    }
}

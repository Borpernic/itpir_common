package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.StatusOsEntity;
import ru.lab729.itpir.model.TypeOsEntity;
import ru.lab729.itpir.service.AbstractStatusOsServiceTest;
import ru.lab729.itpir.service.AbstractTypeOsServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.TypeOsTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaTypeOsServiceTest extends AbstractTypeOsServiceTest {
    @Test
    void testGetWithUser() {
        TypeOsEntity adminOperator = service.getWithUser(ADMIN_TYPE_OS_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_TYPE_OS4);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(TYPE_OS1_ID, ADMIN_ID));

    }
}

package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.TypeBsEntity;
import ru.lab729.itpir.model.TypeOsEntity;
import ru.lab729.itpir.service.AbstractTypeBsServiceTest;
import ru.lab729.itpir.service.AbstractTypeOsServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.TypeBsTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaTypeBsServiceTest extends AbstractTypeBsServiceTest {
    @Test
    void testGetWithUser() {
        TypeBsEntity adminOperator = service.getWithUser(ADMIN_TYPE_BS_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_TYPE_BS3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(TYPE_BS1_ID, ADMIN_ID));

    }
}

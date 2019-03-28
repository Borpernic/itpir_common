package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.TypeAfsEntity;
import ru.lab729.itpir.service.AbstractTypeAfsServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.TypeAfsTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaTypeAfsServiceTest extends AbstractTypeAfsServiceTest {
    @Test
    void testGetWithUser() {
        TypeAfsEntity adminOperator = service.getWithUser(ADMIN_TYPE_AFS_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_TYPE_AFS3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(TYPE_AFS1_ID, ADMIN_ID));

    }
}

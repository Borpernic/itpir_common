package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.InternalNumberEntity;
import ru.lab729.itpir.service.AbstractInternalNumberServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.InternalNumberTestData.*;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaInternalNumberServiceTest extends AbstractInternalNumberServiceTest {
    @Test
    void testGetWithUser() {
        InternalNumberEntity adminOperator = service.getWithUser(ADMIN_INTERNAL_NUMBER_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_INTERNAL_NUMBER_3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(INTERNAL_NUMBER1_ID, ADMIN_ID));

    }
}

package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.OperatorTestData;
import ru.lab729.itpir.UserTestData;

import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.service.AbstractMealServiceTest;
import ru.lab729.itpir.service.AbstractOperatorServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static ru.lab729.itpir.OperatorTestData.*;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaOperatorServiceTest extends AbstractOperatorServiceTest {
    @Test
    void testGetWithUser() {
        OperatorEntity adminOperator = service.getWithUser(ADMIN_OPERATOR_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_OPERATOR3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(OPERATOR1_ID, ADMIN_ID));

    }
}

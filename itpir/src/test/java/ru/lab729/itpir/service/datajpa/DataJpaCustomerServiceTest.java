package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.CustomerEntity;
import ru.lab729.itpir.service.AbstractCustomerServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.CustomerTestData.*;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaCustomerServiceTest extends AbstractCustomerServiceTest {
    @Test
    void testGetWithUser() {
        CustomerEntity adminOperator = service.getWithUser(ADMIN_CUSTOMER_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_CUSTOMER5);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(CUSTOMER1_ID, ADMIN_ID));

    }
}

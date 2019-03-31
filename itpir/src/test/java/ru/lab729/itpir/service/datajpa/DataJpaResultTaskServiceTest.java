package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.ResultTaskEntity;
import ru.lab729.itpir.service.AbstractResultTaskServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.ResultTaskTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaResultTaskServiceTest extends AbstractResultTaskServiceTest {
    @Test
    void testGetWithUser() {
        ResultTaskEntity adminOperator = service.getWithUser(ADMIN_RESULT_TASK_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_RESULT_TASK3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(RESULT_TASK1_ID, ADMIN_ID));

    }
}

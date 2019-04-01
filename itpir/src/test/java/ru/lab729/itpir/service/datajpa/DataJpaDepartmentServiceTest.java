package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.DepartmentEntity;
import ru.lab729.itpir.service.AbstractDepartmentServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.DepartmentTestData.*;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaDepartmentServiceTest extends AbstractDepartmentServiceTest {
    @Test
    void testGetWithUser() {
        DepartmentEntity adminOperator = service.getWithUser(ADMIN_DEPARTMENT_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_DEPARTMENT3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(DEPARTMENT1_ID, ADMIN_ID));

    }
}

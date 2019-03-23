package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.OperatorEntity;
import ru.lab729.itpir.model.RegionEntity;
import ru.lab729.itpir.service.AbstractOperatorServiceTest;
import ru.lab729.itpir.service.AbstractRegionServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.RegionTestData.*;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaRegionServiceTest extends AbstractRegionServiceTest {
    @Test
    void testGetWithUser() {
        RegionEntity adminOperator = service.getWithUser(ADMIN_REGION_ID, ADMIN_ID);
        assertMatch(adminOperator, ADMIN_REGION3);
        UserTestData.assertMatch(adminOperator.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(REGION1_ID, ADMIN_ID));

    }
}

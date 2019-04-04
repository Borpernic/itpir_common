package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.SiteEntity;
import ru.lab729.itpir.service.AbstractSiteServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.SiteTestData.*;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaSiteServiceTest extends AbstractSiteServiceTest {
    @Test
    void testGetWithUser() {
        SiteEntity adminSite = service.getWithUser(ADMIN_SITE_ID, ADMIN_ID);
        assertMatch(adminSite, ADMIN_SITE3);
        UserTestData.assertMatch(adminSite.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(SITE1_ID, ADMIN_ID));

    }
}

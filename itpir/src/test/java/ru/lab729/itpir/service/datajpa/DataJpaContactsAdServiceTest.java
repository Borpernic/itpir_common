package ru.lab729.itpir.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.lab729.itpir.UserTestData;
import ru.lab729.itpir.model.ContactsAdEntity;
import ru.lab729.itpir.service.AbstractContactsAdServiceTest;
import ru.lab729.itpir.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lab729.itpir.ContactsAdTestData.*;
import static ru.lab729.itpir.Profiles.DATAJPA;
import static ru.lab729.itpir.UserTestData.ADMIN;
import static ru.lab729.itpir.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaContactsAdServiceTest extends AbstractContactsAdServiceTest {
    @Test
    void testGetWithUser() {
        ContactsAdEntity adminContactsAd = service.getWithUser(ADMIN_CONTACTS_AD_ID, ADMIN_ID);
        assertMatch(adminContactsAd, ADMIN_CONTACTS_AD3);
        UserTestData.assertMatch(adminContactsAd.getUser(), ADMIN);
    }

    @Test
    void testGetWithUserNotFound() {

        assertThrows(NotFoundException.class, () -> service.getWithUser(CONTACTS_AD1_ID, ADMIN_ID));

    }
}

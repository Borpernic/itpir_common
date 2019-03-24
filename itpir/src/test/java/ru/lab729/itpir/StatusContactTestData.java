package ru.lab729.itpir;


import ru.lab729.itpir.model.RegionEntity;
import ru.lab729.itpir.model.StatusContactsEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class StatusContactTestData {
    public static final int STATUSCONTACT1_ID = START_SEQ + 21;
    public static final int ADMIN_STATUSCONTACT_ID = START_SEQ + 23;

    public static final StatusContactsEntity STATUSCONTACT1 = new StatusContactsEntity(STATUSCONTACT1_ID, "Нет данных");
    public static final StatusContactsEntity STATUSCONTACT2 = new StatusContactsEntity(STATUSCONTACT1_ID + 1, "Не звонить");
    public static final StatusContactsEntity ADMIN_STATUSCONTACT3 = new StatusContactsEntity(ADMIN_STATUSCONTACT_ID, "Звонить для доступа");
    public static final List<StatusContactsEntity> STATUSCONTACTS = Arrays.asList(STATUSCONTACT2, STATUSCONTACT1);
    public static final List<StatusContactsEntity> ALL_STATUSCONTACTS = Arrays.asList(STATUSCONTACT2,ADMIN_STATUSCONTACT3, STATUSCONTACT1 );

    public static StatusContactsEntity getCreated() {
        return new StatusContactsEntity(null, "Созданный статус");
    }

    public static StatusContactsEntity getUpdated() {
        return new StatusContactsEntity(STATUSCONTACT1_ID, "Обновленный статус");
    }

    public static void assertMatch(StatusContactsEntity actual, StatusContactsEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<StatusContactsEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<StatusContactsEntity> actual, StatusContactsEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<StatusContactsEntity> actual, Iterable<StatusContactsEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

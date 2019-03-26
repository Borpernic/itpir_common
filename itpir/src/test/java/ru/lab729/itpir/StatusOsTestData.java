package ru.lab729.itpir;


import ru.lab729.itpir.model.StatusContactsEntity;
import ru.lab729.itpir.model.StatusOsEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class StatusOsTestData {
    public static final int STATUSOS1_ID = START_SEQ + 46;
    public static final int ADMIN_STATUSOS_ID = START_SEQ + 48;

    public static final StatusOsEntity STATUSOS1 = new StatusOsEntity(STATUSOS1_ID, "В работе");
    public static final StatusOsEntity STATUSOS2 = new StatusOsEntity(STATUSOS1_ID + 1, "Заморожен");
    public static final StatusOsEntity ADMIN_STATUSOS3 = new StatusOsEntity(ADMIN_STATUSOS_ID, "Отозван");
    public static final StatusOsEntity ADMIN_STATUSOS4 = new StatusOsEntity(ADMIN_STATUSOS_ID+1, "Отменен");
    public static final List<StatusOsEntity> STATUSOSS = Arrays.asList(STATUSOS1, STATUSOS2);
    public static final List<StatusOsEntity> ALL_STATUSOSS = Arrays.asList( STATUSOS1, STATUSOS2, ADMIN_STATUSOS4,ADMIN_STATUSOS3);

    public static StatusOsEntity getCreated() {
        return new StatusOsEntity(null, "Созданный статус");
    }

    public static StatusOsEntity getUpdated() {
        return new StatusOsEntity(STATUSOS1_ID, "Обновленный статус");
    }

    public static void assertMatch(StatusOsEntity actual, StatusOsEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<StatusOsEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<StatusOsEntity> actual, StatusOsEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<StatusOsEntity> actual, Iterable<StatusOsEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

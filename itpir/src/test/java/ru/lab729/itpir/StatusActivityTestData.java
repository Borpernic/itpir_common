package ru.lab729.itpir;


import ru.lab729.itpir.model.StatusActivityEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class StatusActivityTestData {
    public static final int STATUS_ACTIVITY1_ID = START_SEQ + 175;
    public static final int ADMIN_STATUS_ACTIVITY_ID = START_SEQ + 177;

    public static final StatusActivityEntity STATUS_ACTIVITY1 = new StatusActivityEntity(STATUS_ACTIVITY1_ID, "Нет подтверждения");
    public static final StatusActivityEntity STATUS_ACTIVITY2 = new StatusActivityEntity(STATUS_ACTIVITY1_ID + 1, "В работе");
    public static final StatusActivityEntity ADMIN_STATUS_ACTIVITY3 = new StatusActivityEntity(ADMIN_STATUS_ACTIVITY_ID, "Выполнено");
    public static final StatusActivityEntity ADMIN_STATUS_ACTIVITY4 = new StatusActivityEntity(ADMIN_STATUS_ACTIVITY_ID+1, "Приостановлено");
    public static final StatusActivityEntity ADMIN_STATUS_ACTIVITY5 = new StatusActivityEntity(ADMIN_STATUS_ACTIVITY_ID+2, "Отозвано");
    public static final List<StatusActivityEntity> STATUS_ACTIVITYS = Arrays.asList(STATUS_ACTIVITY2, STATUS_ACTIVITY1);
    public static final List<StatusActivityEntity> ALL_STATUS_ACTIVITYS = Arrays.asList( STATUS_ACTIVITY2,ADMIN_STATUS_ACTIVITY3, STATUS_ACTIVITY1, ADMIN_STATUS_ACTIVITY5,ADMIN_STATUS_ACTIVITY4);

    public static StatusActivityEntity getCreated() {
        return new StatusActivityEntity(null, "Созданный статус");
    }

    public static StatusActivityEntity getUpdated() {
        return new StatusActivityEntity(STATUS_ACTIVITY1_ID, "Обновленный статус");
    }

    public static void assertMatch(StatusActivityEntity actual, StatusActivityEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<StatusActivityEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<StatusActivityEntity> actual, StatusActivityEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<StatusActivityEntity> actual, Iterable<StatusActivityEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

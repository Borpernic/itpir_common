package ru.lab729.itpir;

import ru.lab729.itpir.model.DateChangeStatusEntity;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.ActivityTestData.*;
import static ru.lab729.itpir.StatusActivityTestData.STATUS_ACTIVITY1;
import static ru.lab729.itpir.StatusActivityTestData.STATUS_ACTIVITY2;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class DateChangeStatusTestData {
    public static final int DATE_CHANGE_STATUS1_ID = START_SEQ + 194;
    public static final int ADMIN_DATE_CHANGE_STATUS_ID = START_SEQ + 196;

    public static final DateChangeStatusEntity DATE_CHANGE_STATUS1 = new DateChangeStatusEntity(DATE_CHANGE_STATUS1_ID, ACTIVITY1, of(2019, Month.MAY, 30, 13, 0),
            STATUS_ACTIVITY1, "Задача 1");
    public static final DateChangeStatusEntity DATE_CHANGE_STATUS2 = new DateChangeStatusEntity(DATE_CHANGE_STATUS1_ID + 1, ACTIVITY2, of(2019, Month.MAY, 30, 13, 0),
            STATUS_ACTIVITY1, "Задача 2");
    public static final DateChangeStatusEntity ADMIN_DATE_CHANGE_STATUS3 = new DateChangeStatusEntity(ADMIN_DATE_CHANGE_STATUS_ID, ADMIN_ACTIVITY3, of(2019, Month.MAY, 30, 13, 0),
            STATUS_ACTIVITY1, "Задача 3");

    public static final List<DateChangeStatusEntity> ALL_DATE_CHANGE_STATUSS = Arrays.asList(DATE_CHANGE_STATUS1, DATE_CHANGE_STATUS2, ADMIN_DATE_CHANGE_STATUS3);

    public static DateChangeStatusEntity getCreated() {
        return new DateChangeStatusEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 13, 0),
                STATUS_ACTIVITY2, "Задача new");
    }

    public static DateChangeStatusEntity getUpdated() {
        return new DateChangeStatusEntity(DATE_CHANGE_STATUS1_ID, ACTIVITY1, of(2019, Month.MAY, 30, 13, 0),
                STATUS_ACTIVITY1, "Задача 1 обновленная");
    }

    public static void assertMatch(DateChangeStatusEntity actual, DateChangeStatusEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<DateChangeStatusEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<DateChangeStatusEntity> actual, DateChangeStatusEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<DateChangeStatusEntity> actual, Iterable<DateChangeStatusEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

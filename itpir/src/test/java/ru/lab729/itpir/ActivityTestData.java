package ru.lab729.itpir;


import ru.lab729.itpir.model.ActivityEntity;

import java.math.BigInteger;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.ImplementerTestData.IMPLEMENTER1;
import static ru.lab729.itpir.OsTestData.OS1;
import static ru.lab729.itpir.OsTestData.OS2;
import static ru.lab729.itpir.StatusActivityTestData.STATUS_ACTIVITY1;
import static ru.lab729.itpir.TypeActivityTestData.*;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class ActivityTestData {
    public static final int ACTIVITY1_ID = START_SEQ + 191;
    public static final int ADMIN_ACTIVITY_ID = START_SEQ + 193;

    public static final ActivityEntity ACTIVITY1 = new ActivityEntity(ACTIVITY1_ID, OS1, IMPLEMENTER1, TYPE_ACTIVITY1,
            of(2019, Month.MAY, 30, 13, 0), of(2015, Month.MAY, 30, 13, 0),
            BigInteger.ONE, STATUS_ACTIVITY1, null, "Задача 1");
    public static final ActivityEntity ACTIVITY2 = new ActivityEntity(ACTIVITY1_ID + 1, OS1, IMPLEMENTER1, TYPE_ACTIVITY2,
            of(2019, Month.MAY, 30, 13, 0), of(2015, Month.MAY, 30, 13, 0),
            BigInteger.ONE, STATUS_ACTIVITY1, null, "Задача 2");
    public static final ActivityEntity ADMIN_ACTIVITY3 = new ActivityEntity(ADMIN_ACTIVITY_ID, OS1, IMPLEMENTER1, ADMIN_TYPE_ACTIVITY3,
            of(2019, Month.MAY, 30, 13, 0), of(2015, Month.MAY, 30, 13, 0),
            BigInteger.ONE, STATUS_ACTIVITY1, null, "Задача 3");

    public static final List<ActivityEntity> ALL_ACTIVITYS = Arrays.asList(ACTIVITY1, ACTIVITY2, ADMIN_ACTIVITY3);

    public static ActivityEntity getCreated() {
        return new ActivityEntity(null, OS2, IMPLEMENTER1, TYPE_ACTIVITY1,
                of(2019, Month.MAY, 30, 13, 0), of(2015, Month.MAY, 30, 13, 0),
                BigInteger.ONE, STATUS_ACTIVITY1, null, "Новая Задача");
    }

    public static ActivityEntity getUpdated() {
        return new ActivityEntity(ACTIVITY1_ID, OS1, IMPLEMENTER1, TYPE_ACTIVITY1,
                of(2019, Month.MAY, 30, 13, 0), of(2015, Month.MAY, 30, 13, 0),
                BigInteger.ONE, STATUS_ACTIVITY1, null, "Обновленная Задача 1");
    }

    public static void assertMatch(ActivityEntity actual, ActivityEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<ActivityEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<ActivityEntity> actual, ActivityEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<ActivityEntity> actual, Iterable<ActivityEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

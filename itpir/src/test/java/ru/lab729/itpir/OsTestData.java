package ru.lab729.itpir;


import ru.lab729.itpir.model.OsEntity;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.BandTestData.*;
import static ru.lab729.itpir.CuratorTestData.*;
import static ru.lab729.itpir.InternalNumberTestData.*;
import static ru.lab729.itpir.SiteTestData.*;
import static ru.lab729.itpir.StatusOsTestData.*;
import static ru.lab729.itpir.TypeAfsTestData.*;
import static ru.lab729.itpir.TypeAmsTestData.*;
import static ru.lab729.itpir.TypeBsTestData.*;
import static ru.lab729.itpir.TypeOsTestData.*;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class OsTestData {
    public static final int OS1_ID = START_SEQ + 72;
    public static final int ADMIN_OS_ID = START_SEQ + 74;

    public static final OsEntity OS1 = new OsEntity(OS1_ID, now(), SITE1, INTERNAL_NUMBER_1, CURATOR1, BAND1, TYPE_OS1, TYPE_BS1, TYPE_AMS1, TYPE_AFS1, STATUSOS1, "МТС");
    public static final OsEntity OS2 = new OsEntity(OS1_ID + 1, now(), SITE1, INTERNAL_NUMBER_2, CURATOR2, BAND2, TYPE_OS2, TYPE_BS2, TYPE_AMS2, TYPE_AFS2, STATUSOS2, "HW");
    public static final OsEntity ADMIN_OS3 = new OsEntity(ADMIN_OS_ID, now(), ADMIN_SITE3, ADMIN_INTERNAL_NUMBER_3, ADMIN_CURATOR3, ADMIN_BAND3,
            TYPE_OS3, ADMIN_TYPE_BS3, TYPE_AMS3, ADMIN_TYPE_AFS3, ADMIN_STATUSOS3, "HW");
    public static final List<OsEntity> OSS = Arrays.asList(OS2, OS1);
    public static final List<OsEntity> ALL_OSS = Arrays.asList(OS1, OS2, ADMIN_OS3);

    public static OsEntity getCreated() {
        return new OsEntity(null, now(), SITE2, INTERNAL_NUMBER_1, CURATOR1, BAND1, TYPE_OS1, TYPE_BS1, TYPE_AMS1, TYPE_AFS1, STATUSOS1, "Созданный OS комментарий");
    }

    public static OsEntity getUpdated() {
        return new OsEntity(OS1_ID, now(), SITE1, INTERNAL_NUMBER_1, CURATOR1, BAND1, TYPE_OS1, TYPE_BS1, TYPE_AMS1, TYPE_AFS1, STATUSOS1, "Обновленный OS комментарий");
    }

    public static void assertMatch(OsEntity actual, OsEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<OsEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<OsEntity> actual, OsEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<OsEntity> actual, Iterable<OsEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

package ru.lab729.itpir;


import ru.lab729.itpir.model.PmEntity;
import ru.lab729.itpir.model.StatusContactsEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class PmTestData {
    public static final int PM1_ID = START_SEQ + 27;
    public static final int ADMIN_PM_ID = START_SEQ + 29;

    public static final PmEntity PM1 = new PmEntity(PM1_ID, "Мамонтов","МТС");
    public static final PmEntity PM2 = new PmEntity(PM1_ID + 1, "Козлов","Хуавей");
    public static final PmEntity ADMIN_PM3 = new PmEntity(ADMIN_PM_ID, "Байрамсагатов","WLL");
    public static final List<PmEntity> PMS = Arrays.asList(PM2, PM1);
    public static final List<PmEntity> ALL_PMS = Arrays.asList(ADMIN_PM3, PM2, PM1 );

    public static PmEntity getCreated() {
        return new PmEntity(null, "Созданный PM","Созданный PM комментарий" );
    }

    public static PmEntity getUpdated() {
        return new PmEntity(PM1_ID, "Обновленный PM", "Обновленный PM комментарий");
    }

    public static void assertMatch(PmEntity actual, PmEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<PmEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<PmEntity> actual, PmEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<PmEntity> actual, Iterable<PmEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

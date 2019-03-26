package ru.lab729.itpir;


import ru.lab729.itpir.model.BandEntity;
import ru.lab729.itpir.model.PmEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class BandTestData {
    public static final int BAND1_ID = START_SEQ + 43;
    public static final int ADMIN_BAND_ID = START_SEQ + 45;

    public static final BandEntity BAND1 = new BandEntity(BAND1_ID, "U900","МТС");
    public static final BandEntity BAND2 = new BandEntity(BAND1_ID + 1, "U2100","МТС");
    public static final BandEntity ADMIN_BAND3 = new BandEntity(ADMIN_BAND_ID, "L2600","Вымпелком");
    public static final List<BandEntity> BANDS = Arrays.asList(BAND2, BAND1);
    public static final List<BandEntity> ALL_BANDS = Arrays.asList(ADMIN_BAND3, BAND2, BAND1 );

    public static BandEntity getCreated() {
        return new BandEntity(null, "Созданный BAND","Созданный BAND комментарий" );
    }

    public static BandEntity getUpdated() {
        return new BandEntity(BAND1_ID, "Обновленный PM", "Обновленный BAND комментарий");
    }

    public static void assertMatch(BandEntity actual, BandEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<BandEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<BandEntity> actual, BandEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<BandEntity> actual, Iterable<BandEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

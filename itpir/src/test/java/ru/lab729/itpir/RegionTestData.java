package ru.lab729.itpir;


import ru.lab729.itpir.model.RegionEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class RegionTestData {
    public static final int REGION1_ID = START_SEQ + 13;
    public static final int ADMIN_REGION_ID = START_SEQ + 15;

    public static final RegionEntity REGION1 = new RegionEntity(REGION1_ID, "77", "Москва");
    public static final RegionEntity REGION2 = new RegionEntity(REGION1_ID + 1, "50", "МО");
    public static final RegionEntity ADMIN_REGION3 = new RegionEntity(ADMIN_REGION_ID, "68", "Тверь");
    public static final List<RegionEntity> REGIONS = Arrays.asList(REGION2, REGION1);
    public static final List<RegionEntity> ALL_REGIONS = Arrays.asList(REGION2,ADMIN_REGION3, REGION1 );

    public static RegionEntity getCreated() {
        return new RegionEntity(null, "Созданный регион", "Созданный регион комментарий");
    }

    public static RegionEntity getUpdated() {
        return new RegionEntity(REGION1_ID, "Обновленный регион", "Обновленный регион комментарий");
    }

    public static void assertMatch(RegionEntity actual, RegionEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<RegionEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<RegionEntity> actual, RegionEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<RegionEntity> actual, Iterable<RegionEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

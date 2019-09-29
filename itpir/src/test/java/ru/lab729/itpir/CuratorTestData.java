package ru.lab729.itpir;


import ru.lab729.itpir.model.CuratorEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.OperatorTestData.*;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class CuratorTestData {
    public static final int CURATOR1_ID = START_SEQ + 40;
    public static final int ADMIN_CURATOR_ID = START_SEQ + 42;

    public static final CuratorEntity CURATOR1 = new CuratorEntity(CURATOR1_ID, OPERATOR1, "Иваньков");
    public static final CuratorEntity CURATOR2 = new CuratorEntity(CURATOR1_ID + 1, OPERATOR2, "Сидоров");
    public static final CuratorEntity ADMIN_CURATOR3 = new CuratorEntity(ADMIN_CURATOR_ID, ADMIN_OPERATOR3, "Смирнов");
    public static final List<CuratorEntity> CURATORS = Arrays.asList(CURATOR1, CURATOR2);
    public static final List<CuratorEntity> ALL_CURATORS = Arrays.asList(CURATOR1, CURATOR2, ADMIN_CURATOR3);

    public static CuratorEntity getCreated() {
        return new CuratorEntity(null, OPERATOR1, "Созданный Куратор");
    }

    public static CuratorEntity getUpdated() {
        return new CuratorEntity(CURATOR1_ID, OPERATOR1, "Обновленный Куратор");
    }

    public static void assertMatch(CuratorEntity actual, CuratorEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<CuratorEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<CuratorEntity> actual, CuratorEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<CuratorEntity> actual, Iterable<CuratorEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

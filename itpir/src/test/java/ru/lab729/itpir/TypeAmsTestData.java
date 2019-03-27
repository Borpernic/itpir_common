package ru.lab729.itpir;


import ru.lab729.itpir.model.TypeAmsEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class TypeAmsTestData {
    public static final int TYPE_AMS1_ID = START_SEQ + 60;
    public static final int ADMIN_TYPE_AMS_ID = START_SEQ + 64;

    public static final TypeAmsEntity TYPE_AMS1 = new TypeAmsEntity(TYPE_AMS1_ID, "Столб");
    public static final TypeAmsEntity TYPE_AMS2 = new TypeAmsEntity(TYPE_AMS1_ID + 1, "Башня");
    public static final TypeAmsEntity TYPE_AMS3 = new TypeAmsEntity(TYPE_AMS1_ID + 2, "Мачта");
    public static final TypeAmsEntity TYPE_AMS4 = new TypeAmsEntity(TYPE_AMS1_ID + 3, "Трипод");
    public static final TypeAmsEntity ADMIN_TYPE_AMS5 = new TypeAmsEntity(ADMIN_TYPE_AMS_ID, "Крыша");
    public static final TypeAmsEntity ADMIN_TYPE_AMS6 = new TypeAmsEntity(ADMIN_TYPE_AMS_ID + 1, "Дымовая труба");
    public static final List<TypeAmsEntity> TYPE_AMSS = Arrays.asList(TYPE_AMS2, TYPE_AMS3, TYPE_AMS1, TYPE_AMS4);
    public static final List<TypeAmsEntity> ALL_TYPE_AMSS = Arrays.asList(TYPE_AMS2, ADMIN_TYPE_AMS6, ADMIN_TYPE_AMS5, TYPE_AMS3, TYPE_AMS1, TYPE_AMS4);

    public static TypeAmsEntity getCreated() {
        return new TypeAmsEntity(null, "Созданный тип");
    }

    public static TypeAmsEntity getUpdated() {
        return new TypeAmsEntity(TYPE_AMS1_ID, "Обновленный тип");
    }

    public static void assertMatch(TypeAmsEntity actual, TypeAmsEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<TypeAmsEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<TypeAmsEntity> actual, TypeAmsEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<TypeAmsEntity> actual, Iterable<TypeAmsEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

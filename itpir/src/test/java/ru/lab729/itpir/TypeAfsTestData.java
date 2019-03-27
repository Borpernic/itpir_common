package ru.lab729.itpir;


import ru.lab729.itpir.model.TypeAfsEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class TypeAfsTestData {
    public static final int TYPE_AFS1_ID = START_SEQ + 13;
    public static final int ADMIN_TYPE_AFS_ID = START_SEQ + 15;

    public static final TypeAfsEntity TYPE_AFS1 = new TypeAfsEntity(TYPE_AFS1_ID, "Антенные опоры на фасаде");
    public static final TypeAfsEntity TYPE_AFS2 = new TypeAfsEntity(TYPE_AFS1_ID + 1, "Антенные опоры на пригрузах");
    public static final TypeAfsEntity ADMIN_TYPE_AFS3 = new TypeAfsEntity(ADMIN_TYPE_AFS_ID, "Трипод");
    public static final List<TypeAfsEntity> TYPE_AFSS = Arrays.asList(TYPE_AFS2, TYPE_AFS1);
    public static final List<TypeAfsEntity> ALL_TYPE_AFSS = Arrays.asList(TYPE_AFS2, TYPE_AFS1, ADMIN_TYPE_AFS3);

    public static TypeAfsEntity getCreated() {
        return new TypeAfsEntity(null, "Созданный тип");
    }

    public static TypeAfsEntity getUpdated() {
        return new TypeAfsEntity(TYPE_AFS1_ID, "Обновленный тип");
    }

    public static void assertMatch(TypeAfsEntity actual, TypeAfsEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<TypeAfsEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<TypeAfsEntity> actual, TypeAfsEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<TypeAfsEntity> actual, Iterable<TypeAfsEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

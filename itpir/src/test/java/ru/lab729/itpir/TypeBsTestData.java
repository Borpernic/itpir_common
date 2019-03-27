package ru.lab729.itpir;


import ru.lab729.itpir.model.TypeBsEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class TypeBsTestData {
    public static final int TYPE_BS1_ID = START_SEQ + 55;
    public static final int ADMIN_TYPE_BS_ID = START_SEQ + 57;

    public static final TypeBsEntity TYPE_BS1 = new TypeBsEntity(TYPE_BS1_ID, "Выгородка");
    public static final TypeBsEntity TYPE_BS2 = new TypeBsEntity(TYPE_BS1_ID + 1, "Контейнер на крыше");
    public static final TypeBsEntity ADMIN_TYPE_BS3 = new TypeBsEntity(ADMIN_TYPE_BS_ID, "Контейнер на земле");
    public static final TypeBsEntity ADMIN_TYPE_BS4 = new TypeBsEntity(ADMIN_TYPE_BS_ID + 1, "Климатический шкаф на крыше");
    public static final TypeBsEntity ADMIN_TYPE_BS5 = new TypeBsEntity(ADMIN_TYPE_BS_ID + 2, "Оборудование в серверной АД");
    public static final List<TypeBsEntity> TYPE_BSS = Arrays.asList( TYPE_BS1, TYPE_BS2);
    public static final List<TypeBsEntity> ALL_TYPE_BSS = Arrays.asList(TYPE_BS1, ADMIN_TYPE_BS4, ADMIN_TYPE_BS3, TYPE_BS2, ADMIN_TYPE_BS5);

    public static TypeBsEntity getCreated() {
        return new TypeBsEntity(null, "Созданный тип");
    }

    public static TypeBsEntity getUpdated() {
        return new TypeBsEntity(TYPE_BS1_ID, "Обновленный тип");
    }

    public static void assertMatch(TypeBsEntity actual, TypeBsEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<TypeBsEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<TypeBsEntity> actual, TypeBsEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<TypeBsEntity> actual, Iterable<TypeBsEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

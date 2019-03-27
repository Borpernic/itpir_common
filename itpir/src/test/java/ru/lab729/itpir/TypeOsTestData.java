package ru.lab729.itpir;


import ru.lab729.itpir.model.StatusOsEntity;
import ru.lab729.itpir.model.TypeOsEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class TypeOsTestData {
    public static final int TYPE_OS1_ID = START_SEQ + 50;
    public static final int ADMIN_TYPE_OS_ID = START_SEQ + 53;

    public static final TypeOsEntity TYPE_OS1 = new TypeOsEntity(TYPE_OS1_ID, "Новая стройка");
    public static final TypeOsEntity TYPE_OS2 = new TypeOsEntity(TYPE_OS1_ID + 1, "Модернизация");
    public static final TypeOsEntity TYPE_OS3 = new TypeOsEntity(TYPE_OS1_ID + 2, "Indoor");
    public static final TypeOsEntity ADMIN_TYPE_OS4 = new TypeOsEntity(ADMIN_TYPE_OS_ID, "WLL");
    public static final TypeOsEntity ADMIN_TYPE_OS5 = new TypeOsEntity(ADMIN_TYPE_OS_ID+1, "РРС");
    public static final List<TypeOsEntity> TYPE_OSS = Arrays.asList(TYPE_OS3, TYPE_OS2,TYPE_OS1);
    public static final List<TypeOsEntity> ALL_TYPE_OSS = Arrays.asList( TYPE_OS3, ADMIN_TYPE_OS4,TYPE_OS2, TYPE_OS1,ADMIN_TYPE_OS5);

    public static TypeOsEntity getCreated() {
        return new TypeOsEntity(null, "Созданный тип");
    }

    public static TypeOsEntity getUpdated() {
        return new TypeOsEntity(TYPE_OS1_ID, "Обновленный тип");
    }

    public static void assertMatch(TypeOsEntity actual, TypeOsEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<TypeOsEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<TypeOsEntity> actual, TypeOsEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<TypeOsEntity> actual, Iterable<TypeOsEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

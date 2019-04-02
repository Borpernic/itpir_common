package ru.lab729.itpir;

import ru.lab729.itpir.model.TypeActivityEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class TypeActivityTestData {
    public static final int TYPE_ACTIVITY1_ID = START_SEQ + 180;
    public static final int ADMIN_TYPE_ACTIVITY_ID = START_SEQ + 182;

    public static final TypeActivityEntity TYPE_ACTIVITY1 = new TypeActivityEntity(TYPE_ACTIVITY1_ID, "Запрос исходных данных",
            true, false, false, false, false, false, false, false, false, false, "Тип активности");

    public static final TypeActivityEntity TYPE_ACTIVITY2 = new TypeActivityEntity(TYPE_ACTIVITY1_ID + 1, "Запрос исходного проекта",
            false, true, false, false, false, false, false, false, false, false, "Тип активности");

    public static final TypeActivityEntity ADMIN_TYPE_ACTIVITY3 = new TypeActivityEntity(ADMIN_TYPE_ACTIVITY_ID, "Уточнение исходных данных",
            false, false, true, false, false, false, false, false, false, false, "Тип активности");

    public static final TypeActivityEntity ADMIN_TYPE_ACTIVITY4 = new TypeActivityEntity(ADMIN_TYPE_ACTIVITY_ID + 1, "Обследование",
            false, false, false, true, false, false, false, false, false, false, "Тип активности");

    public static final TypeActivityEntity ADMIN_TYPE_ACTIVITY5 = new TypeActivityEntity(ADMIN_TYPE_ACTIVITY_ID + 2, "Фотоотчет",
            false, false, false, false, true, false, false, false, false, false,  "Тип активности");

    public static final TypeActivityEntity ADMIN_TYPE_ACTIVITY6 = new TypeActivityEntity(ADMIN_TYPE_ACTIVITY_ID + 3, "SSR",
            false, false, false, false, false, true, false, false, false, false,  "Тип активности");

    public static final TypeActivityEntity ADMIN_TYPE_ACTIVITY7 = new TypeActivityEntity(ADMIN_TYPE_ACTIVITY_ID + 4, "TSSR",
            false, false, false, false, false, false, true, false, false, false,  "Тип активности");

    public static final TypeActivityEntity ADMIN_TYPE_ACTIVITY8 = new TypeActivityEntity(ADMIN_TYPE_ACTIVITY_ID + 5, "РД",
            false, false, false, false, false, false, false, true, false, false,  "Тип активности");

    public static final TypeActivityEntity ADMIN_TYPE_ACTIVITY9 = new TypeActivityEntity(ADMIN_TYPE_ACTIVITY_ID + 6, "Сдача РД",
            false, false, false, false, false, false, false, true, false, false,  "Тип активности");

    public static final TypeActivityEntity ADMIN_TYPE_ACTIVITY10 = new TypeActivityEntity(ADMIN_TYPE_ACTIVITY_ID + 7, "ИД",
            false, false, false, false, false, false, false, false, true, false, "Тип активности");

    public static final TypeActivityEntity ADMIN_TYPE_ACTIVITY11 = new TypeActivityEntity(ADMIN_TYPE_ACTIVITY_ID + 8, "Согласование с АД",
            false, false, false, false, false, false, false, false, false, true, "Тип активности");

    public static final List<TypeActivityEntity> TYPE_ACTIVITYS = Arrays.asList(TYPE_ACTIVITY1, TYPE_ACTIVITY2);
    public static final List<TypeActivityEntity> ALL_TYPE_ACTIVITYS = Arrays.asList(TYPE_ACTIVITY1, TYPE_ACTIVITY2,
            ADMIN_TYPE_ACTIVITY3, ADMIN_TYPE_ACTIVITY4, ADMIN_TYPE_ACTIVITY5,
            ADMIN_TYPE_ACTIVITY6, ADMIN_TYPE_ACTIVITY7, ADMIN_TYPE_ACTIVITY8, ADMIN_TYPE_ACTIVITY9, ADMIN_TYPE_ACTIVITY10, ADMIN_TYPE_ACTIVITY11);

    public static TypeActivityEntity getCreated() {
        return new TypeActivityEntity(null, "Созданный entity",
                false, false, false, true, false, false, false, false, false, false,  "Созданный entity комментарий");
    }

    public static TypeActivityEntity getUpdated() {
        return new TypeActivityEntity(TYPE_ACTIVITY1_ID, "Обновленный entity",
                false, false, false, true, false, false, false, false, false, false, "Обновленный entity комментарий");
    }

    public static void assertMatch(TypeActivityEntity actual, TypeActivityEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<TypeActivityEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<TypeActivityEntity> actual, TypeActivityEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<TypeActivityEntity> actual, Iterable<TypeActivityEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

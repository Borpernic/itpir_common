package ru.lab729.itpir;


import ru.lab729.itpir.model.TypeImplementerEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class TypeImplementerTestData {
    public static final int TYPE_IMPLEMENTER1_ID = START_SEQ + 89;
    public static final int ADMIN_TYPE_IMPLEMENTER_ID = START_SEQ + 91;

    public static final TypeImplementerEntity TYPE_IMPLEMENTER1 = new TypeImplementerEntity(TYPE_IMPLEMENTER1_ID, "Оклад", "Штатный");
    public static final TypeImplementerEntity TYPE_IMPLEMENTER2 = new TypeImplementerEntity(TYPE_IMPLEMENTER1_ID + 1, "Сделка", "Штатный");

    public static final TypeImplementerEntity ADMIN_TYPE_IMPLEMENTER3 = new TypeImplementerEntity(ADMIN_TYPE_IMPLEMENTER_ID, "Фриланс", "Физ. лицо");

    public static final List<TypeImplementerEntity> TYPE_IMPLEMENTERS = Arrays.asList(TYPE_IMPLEMENTER1, TYPE_IMPLEMENTER2);
    public static final List<TypeImplementerEntity> ALL_TYPE_IMPLEMENTERS = Arrays.asList(TYPE_IMPLEMENTER1, TYPE_IMPLEMENTER2, ADMIN_TYPE_IMPLEMENTER3);

    public static TypeImplementerEntity getCreated() {
        return new TypeImplementerEntity(null, "Созданный тип", "Созданный тип комментарий");
    }

    public static TypeImplementerEntity getUpdated() {
        return new TypeImplementerEntity(TYPE_IMPLEMENTER1_ID, "Обновленный тип", "Обновленный тип комментарий");
    }

    public static void assertMatch(TypeImplementerEntity actual, TypeImplementerEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<TypeImplementerEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<TypeImplementerEntity> actual, TypeImplementerEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<TypeImplementerEntity> actual, Iterable<TypeImplementerEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

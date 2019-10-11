package ru.lab729.itpir;


import ru.lab729.itpir.model.InternalNumberEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.ProjectTestData.*;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class InternalNumberTestData {
    public static final int INTERNAL_NUMBER1_ID = START_SEQ + 69;
    public static final int ADMIN_INTERNAL_NUMBER_ID = START_SEQ + 71;

    public static final InternalNumberEntity INTERNAL_NUMBER_1 = new InternalNumberEntity(INTERNAL_NUMBER1_ID, PROJECT1, "3568999");
    public static final InternalNumberEntity INTERNAL_NUMBER_2 = new InternalNumberEntity(INTERNAL_NUMBER1_ID + 1, PROJECT2, "3569997");
    public static final InternalNumberEntity ADMIN_INTERNAL_NUMBER_3 = new InternalNumberEntity(ADMIN_INTERNAL_NUMBER_ID, ADMIN_PROJECT3, "3569988");
    public static final List<InternalNumberEntity> INTERNAL_NUMBERS = Arrays.asList(INTERNAL_NUMBER_1, INTERNAL_NUMBER_2);
    public static final List<InternalNumberEntity> ALL_INTERNAL_NUMBERS = Arrays.asList(INTERNAL_NUMBER_1, ADMIN_INTERNAL_NUMBER_3, INTERNAL_NUMBER_2);

    public static InternalNumberEntity getCreated() {
        return new InternalNumberEntity(null, PROJECT1, "Созданный Number");
    }

    public static InternalNumberEntity getUpdated() {
        return new InternalNumberEntity(INTERNAL_NUMBER1_ID, PROJECT1, "Обновленный Number");
    }

    public static void assertMatch(InternalNumberEntity actual, InternalNumberEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<InternalNumberEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<InternalNumberEntity> actual, InternalNumberEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<InternalNumberEntity> actual, Iterable<InternalNumberEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

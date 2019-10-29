package ru.lab729.itpir;


import ru.lab729.itpir.model.ImplementerEntity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.StatusImplementerTestData.*;
import static ru.lab729.itpir.TypeImplementerTestData.*;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class ImplementerTestData {
    public static final int IMPLEMENTER1_ID = START_SEQ + 95;
    public static final int ADMIN_IMPLEMENTER_ID = START_SEQ + 97;

    public static final ImplementerEntity IMPLEMENTER1 = new ImplementerEntity(IMPLEMENTER1_ID, "Ершов С. А.", "8-966-100-31-24", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ");
    public static final ImplementerEntity IMPLEMENTER2 = new ImplementerEntity(IMPLEMENTER1_ID + 1, "Дамаскин М. А.", "8-966-100-31-25", "m.d@email.com", STATUS_IMPLEMENTER2, TYPE_IMPLEMENTER2, BigInteger.ONE, "РС, АС, КМ");
    public static final ImplementerEntity ADMIN_IMPLEMENTER3 = new ImplementerEntity(ADMIN_IMPLEMENTER_ID, "Воробьев Н.", "8-966-100-31-85", "sw.d@email.com", ADMIN_STATUS_IMPLEMENTER3, ADMIN_TYPE_IMPLEMENTER3, BigInteger.ONE, "РС, АС, КМ");
    public static final List<ImplementerEntity> IMPLEMENTERS = Arrays.asList(IMPLEMENTER2, IMPLEMENTER1);
    public static final List<ImplementerEntity> ALL_IMPLEMENTERS = Arrays.asList(ADMIN_IMPLEMENTER3, IMPLEMENTER2, IMPLEMENTER1);

    public static ImplementerEntity getCreated() {
        return new ImplementerEntity(null, "Созданный.", "8-966-100-31-24", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "Созданный комментарий");
    }

    public static ImplementerEntity getUpdated() {
        return new ImplementerEntity(IMPLEMENTER1_ID, "Ершов С. А.", "8-966-100-31-24", "s.e@email.com", STATUS_IMPLEMENTER1, TYPE_IMPLEMENTER1, BigInteger.ONE, "АС, КМ");
    }

    public static void assertMatch(ImplementerEntity actual, ImplementerEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<ImplementerEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<ImplementerEntity> actual, ImplementerEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<ImplementerEntity> actual, Iterable<ImplementerEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

package ru.lab729.itpir;


import ru.lab729.itpir.model.StatusImplementerEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class StatusImplementerTestData {
    public static final int STATUS_IMPLEMENTER1_ID = START_SEQ + 92;
    public static final int ADMIN_STATUS_IMPLEMENTER_ID = START_SEQ + 94;

    public static final StatusImplementerEntity STATUS_IMPLEMENTER1 = new StatusImplementerEntity(STATUS_IMPLEMENTER1_ID, "Готов к работе", "Исполнитель");
    public static final StatusImplementerEntity STATUS_IMPLEMENTER2 = new StatusImplementerEntity(STATUS_IMPLEMENTER1_ID + 1, "Занят", "Исполнитель");

    public static final StatusImplementerEntity ADMIN_STATUS_IMPLEMENTER3 = new StatusImplementerEntity(ADMIN_STATUS_IMPLEMENTER_ID, "Дисквалифицирован", "Исполнитель");

    public static final List<StatusImplementerEntity> STATUS_IMPLEMENTERS = Arrays.asList(STATUS_IMPLEMENTER1, STATUS_IMPLEMENTER2);
    public static final List<StatusImplementerEntity> ALL_STATUS_IMPLEMENTERS = Arrays.asList(STATUS_IMPLEMENTER1, ADMIN_STATUS_IMPLEMENTER3, STATUS_IMPLEMENTER2);

    public static StatusImplementerEntity getCreated() {
        return new StatusImplementerEntity(null, "Созданный статус", "Созданный статус комментарий");
    }

    public static StatusImplementerEntity getUpdated() {
        return new StatusImplementerEntity(STATUS_IMPLEMENTER1_ID, "Обновленный статус", "Обновленный статус комментарий");
    }

    public static void assertMatch(StatusImplementerEntity actual, StatusImplementerEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<StatusImplementerEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<StatusImplementerEntity> actual, StatusImplementerEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<StatusImplementerEntity> actual, Iterable<StatusImplementerEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

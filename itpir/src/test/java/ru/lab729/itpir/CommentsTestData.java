package ru.lab729.itpir;


import ru.lab729.itpir.model.CommentsEntity;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.ImplementerTestData.IMPLEMENTER1;
import static ru.lab729.itpir.OsTestData.OS1;
import static ru.lab729.itpir.OsTestData.OS2;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class CommentsTestData {
    public static final int COMMENTS1_ID = START_SEQ + 201;
    public static final int ADMIN_COMMENTS_ID = START_SEQ + 203;

    public static final CommentsEntity COMMENTS1 = new CommentsEntity(COMMENTS1_ID, OS1, IMPLEMENTER1,
            of(2019, Month.MAY, 30, 10, 0), "АД просит увеличения арендной платы.");
    public static final CommentsEntity COMMENTS2 = new CommentsEntity(COMMENTS1_ID + 1, OS2, IMPLEMENTER1,
            of(2019, Month.MAY, 30, 10, 0), "Закзчик просит связаться с АД.");
    public static final CommentsEntity ADMIN_COMMENTS3 = new CommentsEntity(ADMIN_COMMENTS_ID, OS2, IMPLEMENTER1,
            of(2019, Month.MAY, 30, 10, 0), "АД отказался согласовывать АОП.");


    public static final List<CommentsEntity> ALL_COMMENTSS = Arrays.asList(COMMENTS1, COMMENTS2, ADMIN_COMMENTS3);

    public static CommentsEntity getCreated() {
        return new CommentsEntity(null, OS1, IMPLEMENTER1,
                of(2019, Month.MAY, 30, 10, 0), "Новый комментарий");
    }

    public static CommentsEntity getUpdated() {
        return new CommentsEntity(COMMENTS1_ID, OS1, IMPLEMENTER1,
                of(2019, Month.MAY, 30, 10, 0), "Обновленный комментарий");
    }

    public static void assertMatch(CommentsEntity actual, CommentsEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<CommentsEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<CommentsEntity> actual, CommentsEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<CommentsEntity> actual, Iterable<CommentsEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

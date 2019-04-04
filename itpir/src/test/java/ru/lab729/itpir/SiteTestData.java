package ru.lab729.itpir;

import ru.lab729.itpir.model.SiteEntity;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.OperatorTestData.*;
import static ru.lab729.itpir.RegionTestData.REGION1;
import static ru.lab729.itpir.RegionTestData.REGION2;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class SiteTestData {
    public static final int SITE1_ID = START_SEQ + 16;
    public static final int ADMIN_SITE_ID = START_SEQ + 18;

    public static final SiteEntity SITE1 = new SiteEntity(SITE1_ID, "12790", "Будапешт", OPERATOR1, REGION1,
            of(2018, Month.MAY, 30, 13, 0),
            "Пекин", "Авиамоторная", "28С", " БС 1279");
    public static final SiteEntity SITE2 = new SiteEntity(SITE1_ID+1, "12800", "Будапешт2", OPERATOR1, REGION1,
            of(2018, Month.MAY, 30, 13, 0),
            "Пекин", "Авиамоторная", "28С", " БС 1280");
    public static final SiteEntity ADMIN_SITE3 = new SiteEntity(ADMIN_SITE_ID, "12790", "Будапешт", OPERATOR1, REGION1,
            of(2018, Month.MAY, 30, 13, 0),
            "Пекин", "Авиамоторная", "28С", " БС 1279");
    public static final SiteEntity ADMIN_SITE4 = new SiteEntity(ADMIN_SITE_ID+1, "12790", "Будапешт2", OPERATOR2, REGION1,
            of(2018, Month.MAY, 30, 13, 0),
            "Пекин", "Авиамоторная", "28С", " БС 1279");
    public static final SiteEntity ADMIN_SITE5 = new SiteEntity(ADMIN_SITE_ID+2, "12800", "Будапешт3", ADMIN_OPERATOR3, REGION2,
            of(2018, Month.MAY, 30, 13, 0),
            "Пекин", "Авиамоторная", "28С", " БС 1280");

    public static final List<SiteEntity> SITES = Arrays.asList(SITE1, SITE2);
    public static final List<SiteEntity> ALL_SITES = Arrays.asList(ADMIN_SITE4, ADMIN_SITE5,SITE1,  SITE2,ADMIN_SITE3);

    public static SiteEntity getCreated() {
        return new SiteEntity(null, "00000", "Созданное название", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0), "Созданный город", "Созданная улица", "00000", " Созданный комментарий 1280");

    }

    public static SiteEntity getUpdated() {
        return new SiteEntity(SITE1_ID, "77777", "Обновленное название", OPERATOR1, REGION1,
                of(2018, Month.MAY, 30, 13, 0), "Обновленный город", "Обновленная улица",
                "88888", " Обновленный комментарий 1280");
    }

    public static void assertMatch(SiteEntity actual, SiteEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<SiteEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<SiteEntity> actual, SiteEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<SiteEntity> actual, Iterable<SiteEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

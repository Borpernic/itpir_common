package ru.lab729.itpir;

import ru.lab729.itpir.model.NomenclatureWorksEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class NomenclatureWorksTestData {
    public static final int NOMENCLATURE_WORKS1_ID = START_SEQ + 75;
    public static final int ADMIN_NOMENCLATURE_WORKS_ID = START_SEQ + 78;

    public static final NomenclatureWorksEntity NOMENCLATURE_WORKS1 = new NomenclatureWorksEntity(NOMENCLATURE_WORKS1_ID, "Обследование ОС", "Обследование");
    public static final NomenclatureWorksEntity NOMENCLATURE_WORKS2 = new NomenclatureWorksEntity(NOMENCLATURE_WORKS1_ID + 1, "Разработка SSR", "Обследование");
    public static final NomenclatureWorksEntity NOMENCLATURE_WORKS3 = new NomenclatureWorksEntity(NOMENCLATURE_WORKS1_ID + 2, "Разработка TSSR", "Обследование");
    public static final NomenclatureWorksEntity ADMIN_NOMENCLATURE_WORKS4 = new NomenclatureWorksEntity(ADMIN_NOMENCLATURE_WORKS_ID, "Разработка ТЗ", "ПД/РД");
    public static final NomenclatureWorksEntity ADMIN_NOMENCLATURE_WORKS5 = new NomenclatureWorksEntity(ADMIN_NOMENCLATURE_WORKS_ID + 1, "Разработка ПЗ", "ПД/РД");
    public static final NomenclatureWorksEntity ADMIN_NOMENCLATURE_WORKS6 = new NomenclatureWorksEntity(ADMIN_NOMENCLATURE_WORKS_ID + 2, "Разработка РС", "ПД/РД");
    public static final NomenclatureWorksEntity ADMIN_NOMENCLATURE_WORKS7 = new NomenclatureWorksEntity(ADMIN_NOMENCLATURE_WORKS_ID + 3, "Разработка ЭМ", "ПД/РД");
    public static final NomenclatureWorksEntity ADMIN_NOMENCLATURE_WORKS8 = new NomenclatureWorksEntity(ADMIN_NOMENCLATURE_WORKS_ID + 4, "Разработка ЭС", "ПД/РД");
    public static final NomenclatureWorksEntity ADMIN_NOMENCLATURE_WORKS9 = new NomenclatureWorksEntity(ADMIN_NOMENCLATURE_WORKS_ID + 5, "Разработка АС", "ПД/РД");
    public static final NomenclatureWorksEntity ADMIN_NOMENCLATURE_WORKS10 = new NomenclatureWorksEntity(ADMIN_NOMENCLATURE_WORKS_ID + 6, "Разработка КМ", "ПД/РД");
    public static final NomenclatureWorksEntity ADMIN_NOMENCLATURE_WORKS11 = new NomenclatureWorksEntity(ADMIN_NOMENCLATURE_WORKS_ID + 7, "Разработка РРС", "ПД/РД");
    public static final NomenclatureWorksEntity ADMIN_NOMENCLATURE_WORKS12 = new NomenclatureWorksEntity(ADMIN_NOMENCLATURE_WORKS_ID + 8, "Разработка РРС1", "ПД/РД");
    public static final NomenclatureWorksEntity ADMIN_NOMENCLATURE_WORKS13 = new NomenclatureWorksEntity(ADMIN_NOMENCLATURE_WORKS_ID + 9, "Разработка ДП", "ПД/РД");
    public static final NomenclatureWorksEntity ADMIN_NOMENCLATURE_WORKS14 = new NomenclatureWorksEntity(ADMIN_NOMENCLATURE_WORKS_ID + 10, "Разработка ПРА", "ПД/РД");
    public static final List<NomenclatureWorksEntity> NOMENCLATURE_WORKSS = Arrays.asList(NOMENCLATURE_WORKS1, NOMENCLATURE_WORKS2, NOMENCLATURE_WORKS3);
    public static final List<NomenclatureWorksEntity> ALL_NOMENCLATURE_WORKSS = Arrays.asList(NOMENCLATURE_WORKS1, NOMENCLATURE_WORKS2, NOMENCLATURE_WORKS3,
            ADMIN_NOMENCLATURE_WORKS4, ADMIN_NOMENCLATURE_WORKS5, ADMIN_NOMENCLATURE_WORKS6, ADMIN_NOMENCLATURE_WORKS7, ADMIN_NOMENCLATURE_WORKS8, ADMIN_NOMENCLATURE_WORKS9,
            ADMIN_NOMENCLATURE_WORKS10, ADMIN_NOMENCLATURE_WORKS11, ADMIN_NOMENCLATURE_WORKS12, ADMIN_NOMENCLATURE_WORKS13, ADMIN_NOMENCLATURE_WORKS14);

    public static NomenclatureWorksEntity getCreated() {
        return new NomenclatureWorksEntity(null, "Созданный NOMENCLATURE_WORKS", "Созданный NOMENCLATURE_WORKS комментарий");
    }

    public static NomenclatureWorksEntity getUpdated() {
        return new NomenclatureWorksEntity(NOMENCLATURE_WORKS1_ID, "Обновленный NOMENCLATURE_WORKS", "Обновленный NOMENCLATURE_WORKS комментарий");
    }

    public static void assertMatch(NomenclatureWorksEntity actual, NomenclatureWorksEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<NomenclatureWorksEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<NomenclatureWorksEntity> actual, NomenclatureWorksEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<NomenclatureWorksEntity> actual, Iterable<NomenclatureWorksEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

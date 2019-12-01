package ru.lab729.itpir;


import ru.lab729.itpir.model.TzpEntity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.TypeImplementerTestData.TYPE_IMPLEMENTER1;
import static ru.lab729.itpir.TypeOsTestData.TYPE_OS1;
import static ru.lab729.itpir.TypeOsTestData.TYPE_OS2;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class TzpTestData {
    public static final int TZP1_ID = START_SEQ + 98;
    public static final int ADMIN_TZP_ID = START_SEQ + 120;

    public static final TzpEntity TZP1 = new TzpEntity(TZP1_ID, "1. Обследование площадки, сбор исходных данных, составление и согласование Акта обследования с Заказчиком при модернизации существующего объекта",
            "акт", BigInteger.valueOf(4800), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация");
    public static final TzpEntity TZP2 = new TzpEntity(TZP1_ID + 1, "1.1. Обследование площадки, сбор исходных данных (модернизация БС).",
            "шт.", BigInteger.valueOf(1400), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация");
    public static final TzpEntity TZP3 = new TzpEntity(TZP1_ID + 2, "1.2. Подготовка отчета по обследованию площадки SSR",
            "шт.", BigInteger.valueOf(1300), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация");
    public static final TzpEntity TZP4 = new TzpEntity(TZP1_ID + 3, "1.3. Составление и согласование Акта обследования с Заказчиком при модернизации существующего объекта",
            "акт", BigInteger.valueOf(2100), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация");
    public static final TzpEntity TZP5 = new TzpEntity(TZP1_ID + 4, "2. Обследование площадки, сбор исходных данных, составление и согласование Акта обследования с (новое строительство БС).",
            "акт", BigInteger.valueOf(4800), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity TZP6 = new TzpEntity(TZP1_ID + 5, "2.1. Обследование площадки, сбор исходных данных (новое строительство БС).",
            "шт.", BigInteger.valueOf(1600), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity TZP7 = new TzpEntity(TZP1_ID + 6, "2.2. Составление и согласование Акта обследования с Заказчиком для новой стройки",
            "шт.", BigInteger.valueOf(3200), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity TZP8 = new TzpEntity(TZP1_ID + 7, "3. Разработка основного комплекта рабочих чертежей марки АС при выполнении работ по модернизации",
            "альбом", BigInteger.valueOf(1400), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация");
    public static final TzpEntity TZP9 = new TzpEntity(TZP1_ID + 8, "4. Разработка основного комплекта рабочих чертежей марки РС при модернизации БС",
            "альбом", BigInteger.valueOf(1400), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация");
    public static final TzpEntity TZP10 = new TzpEntity(TZP1_ID + 9, "5. Разработка основного комплекта рабочих чертежей марки ЭМ при выполнении работ по модернизации БС",
            "альбом", BigInteger.valueOf(1400), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация");
    public static final TzpEntity TZP11 = new TzpEntity(TZP1_ID + 10, "6. Разработка основного комплекта рабочих чертежей марки РРС",
            "альбом", BigInteger.valueOf(1400), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация");
    public static final TzpEntity TZP12 = new TzpEntity(TZP1_ID + 11, "7. Разработка основного комплекта рабочих чертежей марки РРС1",
            "альбом", BigInteger.valueOf(1400), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация");
    public static final TzpEntity TZP13 = new TzpEntity(TZP1_ID + 12, "8. Разработка основного комплекта рабочих чертежей марки ЭС (включая ТУ на электроснабжение + акт разграничения БП)",
            "альбом", BigInteger.valueOf(1400), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity TZP14 = new TzpEntity(TZP1_ID + 13, "9. Разработка основного комплекта рабочих чертежей марки КМ",
            "альбом", BigInteger.valueOf(1400), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity TZP15 = new TzpEntity(TZP1_ID + 14, "10. Разработка основного комплекта рабочих чертежей марки КЖ",
            "альбом", BigInteger.valueOf(1400), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity TZP16 = new TzpEntity(TZP1_ID + 15, "11. Разработка основного комплекта рабочих чертежей марки МЗ",
            "альбом", BigInteger.valueOf(1400), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity TZP17 = new TzpEntity(TZP1_ID + 16, "12. Разработка основного комплекта рабочих чертежей марки ОВ",
            "альбом", BigInteger.valueOf(1400), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity TZP18 = new TzpEntity(TZP1_ID + 17, "13. Разработка основного комплекта рабочих чертежей марки ООС",
            "альбом", BigInteger.valueOf(1400), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity TZP19 = new TzpEntity(TZP1_ID + 18, "14. Марка ТХ (Технологические решения)",
            "альбом", BigInteger.valueOf(1400), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity TZP20 = new TzpEntity(TZP1_ID + 19, "15. Дополнительные проектные работы",
            "шт.", BigInteger.valueOf(500), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity TZP21 = new TzpEntity(TZP1_ID + 20, "16. ТУ на электроснабжение + акт разграничения БП",
            "комплект", BigInteger.valueOf(500), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity TZP22 = new TzpEntity(TZP1_ID + 21, "17. Согласование ПТР с АД",
            "шт.", BigInteger.valueOf(500), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity ADMIN_TZP23 = new TzpEntity(ADMIN_TZP_ID, "18. Обследование площадки, сбор исходных данных. Интервал РРС. (2 БС) WLL",
            "шт.", BigInteger.valueOf(500), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");
    public static final TzpEntity ADMIN_TZP24 = new TzpEntity(ADMIN_TZP_ID + 1, "19. Подготовка отчета по радио разведки. ПРА. WLL",
            "шт.", BigInteger.valueOf(500), TYPE_OS1, TYPE_IMPLEMENTER1, "Новая стройка");

    public static final List<TzpEntity> TZPS = Arrays.asList(TZP1, TZP2, TZP3, TZP4, TZP5, TZP6, TZP7, TZP8, TZP9, TZP10,
            TZP11, TZP12, TZP13, TZP14, TZP15, TZP16, TZP17, TZP18, TZP19, TZP20, TZP21, TZP22);
    public static final List<TzpEntity> ALL_TZPS = Arrays.asList(TZP1, TZP2, TZP3, TZP4, TZP5, TZP6, TZP7, TZP8, TZP9, TZP10,
            TZP11, TZP12, TZP13, TZP14, TZP15, TZP16, TZP17, TZP18, TZP19, TZP20, TZP21, TZP22, ADMIN_TZP23, ADMIN_TZP24);

    public static TzpEntity getCreated() {
        return new TzpEntity(null, "Созданный", "акт", BigInteger.valueOf(777), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация созданный");
    }

    public static TzpEntity getUpdated() {
        return new TzpEntity(TZP1_ID, "Обновленный", "акт", BigInteger.valueOf(777), TYPE_OS2, TYPE_IMPLEMENTER1, "Модернизация созданный");
    }

    public static void assertMatch(TzpEntity actual, TzpEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<TzpEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<TzpEntity> actual, TzpEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<TzpEntity> actual, Iterable<TzpEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

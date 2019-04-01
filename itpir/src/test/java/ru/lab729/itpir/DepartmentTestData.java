package ru.lab729.itpir;

import ru.lab729.itpir.model.DepartmentEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class DepartmentTestData {
    public static final int DEPARTMENT1_ID = START_SEQ + 163;
    public static final int ADMIN_DEPARTMENT_ID = START_SEQ + 165;

    public static final DepartmentEntity DEPARTMENT1 = new DepartmentEntity(DEPARTMENT1_ID, "Проектировщик", "Подразделение");
    public static final DepartmentEntity DEPARTMENT2 = new DepartmentEntity(DEPARTMENT1_ID + 1, "ГИП", "Подразделение");

    public static final DepartmentEntity ADMIN_DEPARTMENT3 = new DepartmentEntity(ADMIN_DEPARTMENT_ID, "PM", "Подразделение");
    public static final DepartmentEntity ADMIN_DEPARTMENT4 = new DepartmentEntity(ADMIN_DEPARTMENT_ID + 1, "АД", "Подразделение");
    public static final DepartmentEntity ADMIN_DEPARTMENT5 = new DepartmentEntity(ADMIN_DEPARTMENT_ID + 2, "Куратор", "Подразделение");
    public static final DepartmentEntity ADMIN_DEPARTMENT6 = new DepartmentEntity(ADMIN_DEPARTMENT_ID + 3, "РРС", "Подразделение");
    public static final DepartmentEntity ADMIN_DEPARTMENT7 = new DepartmentEntity(ADMIN_DEPARTMENT_ID + 4, "ВЭП", "Подразделение");
    public static final DepartmentEntity ADMIN_DEPARTMENT8 = new DepartmentEntity(ADMIN_DEPARTMENT_ID + 5, "ПЭП", "Подразделение");
    public static final DepartmentEntity ADMIN_DEPARTMENT9 = new DepartmentEntity(ADMIN_DEPARTMENT_ID + 6, "СМР", "Подразделение");
    public static final DepartmentEntity ADMIN_DEPARTMENT10 = new DepartmentEntity(ADMIN_DEPARTMENT_ID + 7, "Оператор", "Подразделение");
    public static final DepartmentEntity ADMIN_DEPARTMENT11 = new DepartmentEntity(ADMIN_DEPARTMENT_ID + 8, "Заказчик", "Подразделение");
    public static final DepartmentEntity ADMIN_DEPARTMENT12 = new DepartmentEntity(ADMIN_DEPARTMENT_ID + 9, "Договорник", "Подразделение");

    public static final List<DepartmentEntity> DEPARTMENTS = Arrays.asList(DEPARTMENT2, DEPARTMENT1);
    public static final List<DepartmentEntity> ALL_DEPARTMENTS = Arrays.asList(ADMIN_DEPARTMENT3, ADMIN_DEPARTMENT4,
            ADMIN_DEPARTMENT7, DEPARTMENT2, ADMIN_DEPARTMENT12, ADMIN_DEPARTMENT11, ADMIN_DEPARTMENT5,
            ADMIN_DEPARTMENT10, DEPARTMENT1, ADMIN_DEPARTMENT8, ADMIN_DEPARTMENT6, ADMIN_DEPARTMENT9);

    public static DepartmentEntity getCreated() {
        return new DepartmentEntity(null, "Созданный entity", "Созданный entity комментарий");
    }

    public static DepartmentEntity getUpdated() {
        return new DepartmentEntity(DEPARTMENT1_ID, "Обновленный entity", "Обновленный entity комментарий");
    }

    public static void assertMatch(DepartmentEntity actual, DepartmentEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<DepartmentEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<DepartmentEntity> actual, DepartmentEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<DepartmentEntity> actual, Iterable<DepartmentEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

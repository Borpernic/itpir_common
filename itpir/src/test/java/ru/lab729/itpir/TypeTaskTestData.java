package ru.lab729.itpir;


import ru.lab729.itpir.model.TypeTaskEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class TypeTaskTestData {
    public static final int TYPE_TASK1_ID = START_SEQ + 122;
    public static final int ADMIN_TYPE_TASK_ID = START_SEQ + 125;

    public static final TypeTaskEntity TYPE_TASK1 = new TypeTaskEntity(TYPE_TASK1_ID, "Запросить информацию", "Тип задачи");
    public static final TypeTaskEntity TYPE_TASK2 = new TypeTaskEntity(TYPE_TASK1_ID + 1, "Получен заказ от PM", "Тип задачи");
    public static final TypeTaskEntity TYPE_TASK3 = new TypeTaskEntity(TYPE_TASK1_ID + 2, "Выдать в работу исполнителю", "Тип задачи");

    public static final TypeTaskEntity ADMIN_TYPE_TASK4 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID, "Проектирование/разработка", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK5 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 1, "Отправка заказчику", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK6 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 2, "Отправка исполнителю", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK7 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 3, "Отправка оператору", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK8 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 4, "Отправка РРС", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK9 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 5, "Отправка ВЭП", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK10 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 6, "Отправка ПЭП", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK11 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 7, "Отправка СМР", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK12 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 8, "Отправка АД", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK13 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 9, "Получение замечаний", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK14 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 10, "Исправление замечаний", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK15 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 11, "Подготовить документы в бумаге", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK16 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 12, "Передать документы в бумаге", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK17 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 13, "Оплатить", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK18 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 14, "Написать письмо", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK19 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 15, "Позвонить", "Тип задачи");
    public static final TypeTaskEntity ADMIN_TYPE_TASK20 = new TypeTaskEntity(ADMIN_TYPE_TASK_ID + 16, "Встретиться", "Тип задачи");


    public static final List<TypeTaskEntity> TYPE_TASKS = Arrays.asList(TYPE_TASK1, TYPE_TASK2, TYPE_TASK3);
    public static final List<TypeTaskEntity> ALL_TYPE_TASKS = Arrays.asList(TYPE_TASK1, TYPE_TASK2, TYPE_TASK3,
            ADMIN_TYPE_TASK4, ADMIN_TYPE_TASK5, ADMIN_TYPE_TASK6, ADMIN_TYPE_TASK7, ADMIN_TYPE_TASK8, ADMIN_TYPE_TASK9,
            ADMIN_TYPE_TASK10, ADMIN_TYPE_TASK11, ADMIN_TYPE_TASK12, ADMIN_TYPE_TASK13, ADMIN_TYPE_TASK14,
            ADMIN_TYPE_TASK15, ADMIN_TYPE_TASK16, ADMIN_TYPE_TASK17, ADMIN_TYPE_TASK18, ADMIN_TYPE_TASK19, ADMIN_TYPE_TASK20);

    public static TypeTaskEntity getCreated() {
        return new TypeTaskEntity(null, "Созданный тип", "Созданный тип комментарий");
    }

    public static TypeTaskEntity getUpdated() {
        return new TypeTaskEntity(TYPE_TASK1_ID, "Обновленный тип", "Обновленный тип комментарий");
    }

    public static void assertMatch(TypeTaskEntity actual, TypeTaskEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<TypeTaskEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<TypeTaskEntity> actual, TypeTaskEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<TypeTaskEntity> actual, Iterable<TypeTaskEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

package ru.lab729.itpir;


import ru.lab729.itpir.model.ResultTaskEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class ResultTaskTestData {
    public static final int RESULT_TASK1_ID = START_SEQ + 142;
    public static final int ADMIN_RESULT_TASK_ID = START_SEQ + 144;

    public static final ResultTaskEntity RESULT_TASK1 = new ResultTaskEntity(RESULT_TASK1_ID, "Отправлено письмо", "Результат задачи");
    public static final ResultTaskEntity RESULT_TASK2 = new ResultTaskEntity(RESULT_TASK1_ID + 1, "Заказ обработан", "Результат задачи");

    public static final ResultTaskEntity ADMIN_RESULT_TASK3 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID, "Исходные данные отправлены исполнителю", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK4 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 1, "Подготовлен запрос исходных данных", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK5 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 2, "Подготовлен запрос исходного проекта", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK6 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 3, "Получены документы", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK7 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 4, "Замечания исправлены", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK8 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 5, "Документы отправлены заказчику", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK9 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 6, "Документы отправлены оператору", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK10 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 7, "Документы отправлены АД", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK11 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 8, "Документы отправлены заказчику после исправления", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK12 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 9, "Документы отправлены оператору после исправления", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK13 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 10, "Документы отправлены АД после исправления", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK14 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 11, "Документы подготовлены", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK15 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 12, "Документы переданы", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK16 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 13, "Оплачено", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK17 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 14, "Письмо отправлено", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK18 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 15, "Звонок выполнен", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK19 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 16, "Встреча проведена", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK20 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 17, "Работы перенесены", "Результат задачи");
    public static final ResultTaskEntity ADMIN_RESULT_TASK21 = new ResultTaskEntity(ADMIN_RESULT_TASK_ID + 18, "Работы отменены", "Результат задачи");


    public static final List<ResultTaskEntity> RESULT_TASKS = Arrays.asList(RESULT_TASK1, RESULT_TASK2);
    public static final List<ResultTaskEntity> ALL_RESULT_TASKS = Arrays.asList(RESULT_TASK1, RESULT_TASK2,
            ADMIN_RESULT_TASK3, ADMIN_RESULT_TASK4, ADMIN_RESULT_TASK5, ADMIN_RESULT_TASK6, ADMIN_RESULT_TASK7, ADMIN_RESULT_TASK8, ADMIN_RESULT_TASK9,
            ADMIN_RESULT_TASK10, ADMIN_RESULT_TASK11, ADMIN_RESULT_TASK12, ADMIN_RESULT_TASK13, ADMIN_RESULT_TASK14,
            ADMIN_RESULT_TASK15, ADMIN_RESULT_TASK16, ADMIN_RESULT_TASK17, ADMIN_RESULT_TASK18, ADMIN_RESULT_TASK19, ADMIN_RESULT_TASK20, ADMIN_RESULT_TASK21);

    public static ResultTaskEntity getCreated() {
        return new ResultTaskEntity(null, "Созданный entity", "Созданный entity комментарий");
    }

    public static ResultTaskEntity getUpdated() {
        return new ResultTaskEntity(RESULT_TASK1_ID, "Обновленный entity", "Обновленный entity комментарий");
    }

    public static void assertMatch(ResultTaskEntity actual, ResultTaskEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<ResultTaskEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<ResultTaskEntity> actual, ResultTaskEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<ResultTaskEntity> actual, Iterable<ResultTaskEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

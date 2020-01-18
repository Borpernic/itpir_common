package ru.lab729.itpir;


import ru.lab729.itpir.model.TaskEntity;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.ActivityTestData.ACTIVITY1;
import static ru.lab729.itpir.DepartmentTestData.*;
import static ru.lab729.itpir.ResultTaskTestData.*;
import static ru.lab729.itpir.TypeTaskTestData.TYPE_TASK1;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class TaskTestData {
    public static final int TASK1_ID = START_SEQ + 197;
    public static final int ADMIN_TASK_ID = START_SEQ + 199;

    public static final TaskEntity TASK1 = new TaskEntity(TASK1_ID, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
            TYPE_TASK1, DEPARTMENT1, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
            RESULT_TASK1, "Задача 1");
    public static final TaskEntity TASK2 = new TaskEntity(TASK1_ID + 1, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
            TYPE_TASK1, DEPARTMENT2, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
            RESULT_TASK2, "Задача 2");
    public static final TaskEntity ADMIN_TASK3 = new TaskEntity(ADMIN_TASK_ID, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
            TYPE_TASK1, ADMIN_DEPARTMENT3, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
            ADMIN_RESULT_TASK3, "Задача 3");
    public static final TaskEntity ADMIN_TASK4 = new TaskEntity(ADMIN_TASK_ID + 1, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
            TYPE_TASK1, ADMIN_DEPARTMENT4, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
            ADMIN_RESULT_TASK4, "Задача 4");


    public static final List<TaskEntity> ALL_TASKS = Arrays.asList(TASK1, TASK2, ADMIN_TASK3, ADMIN_TASK4);

    public static TaskEntity getCreated() {
        return new TaskEntity(null, ACTIVITY1, of(2019, Month.MAY, 30, 10, 0),
                TYPE_TASK1, ADMIN_DEPARTMENT4, of(2019, Month.MAY, 30, 10, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
                ADMIN_RESULT_TASK4, "Задача новая");
    }

    public static TaskEntity getUpdated() {
        return new TaskEntity(TASK1_ID, ACTIVITY1, of(2019, Month.MAY, 30, 12, 0),
                TYPE_TASK1, ADMIN_DEPARTMENT4, of(2019, Month.MAY, 30, 12, 0), true, true, of(2019, Month.MAY, 30, 10, 0),
                ADMIN_RESULT_TASK5, "Задача обновлена");
    }

    public static void assertMatch(TaskEntity actual, TaskEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<TaskEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<TaskEntity> actual, TaskEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<TaskEntity> actual, Iterable<TaskEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

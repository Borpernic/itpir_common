package ru.lab729.itpir;


import ru.lab729.itpir.model.ProjectEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.CustomerTestData.*;
import static ru.lab729.itpir.PmTestData.*;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class ProjectTestData {
    public static final int PROJECT1_ID = START_SEQ + 37;
    public static final int ADMIN_PROJECT_ID = START_SEQ + 39;

    public static final ProjectEntity PROJECT1 = new ProjectEntity(PROJECT1_ID, "МТС", PM1, CUSTOMER1, "Столб");
    public static final ProjectEntity PROJECT2 = new ProjectEntity(PROJECT1_ID + 1, "Хуавей", PM2, ADMIN_CUSTOMER5, "SWAP");
    public static final ProjectEntity ADMIN_PROJECT3 = new ProjectEntity(ADMIN_PROJECT_ID, "WLL", ADMIN_PM3, CUSTOMER2, "РРС");

    public static final List<ProjectEntity> PROJECTS = Arrays.asList(PROJECT1, PROJECT2);
    public static final List<ProjectEntity> ALL_PROJECTS = Arrays.asList(ADMIN_PROJECT3, PROJECT1, PROJECT2);

    public static ProjectEntity getCreated() {
        return new ProjectEntity(null, "Созданный проект", PM1, CUSTOMER1, "Созданный комментарий");
    }

    public static ProjectEntity getUpdated() {
        return new ProjectEntity(PROJECT1_ID, "Обновленный проект", PM1, CUSTOMER1, "Обновленный комментарий");
    }

    public static void assertMatch(ProjectEntity actual, ProjectEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<ProjectEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<ProjectEntity> actual, ProjectEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<ProjectEntity> actual, Iterable<ProjectEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

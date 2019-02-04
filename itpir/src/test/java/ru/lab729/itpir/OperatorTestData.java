package ru.lab729.itpir;

import ru.lab729.itpir.model.Meal;
import ru.lab729.itpir.model.OperatorEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class OperatorTestData {
    public static final int OPERATOR1_ID = START_SEQ + 10;
    public static final int ADMIN_OPERATOR_ID = START_SEQ + 12;

    public static final OperatorEntity OPERATOR1 = new OperatorEntity(OPERATOR1_ID, "МТС", "Оператор МТС");
    public static final OperatorEntity OPERATOR2 = new OperatorEntity(OPERATOR1_ID + 1, "'Билайн'", "Оператор Билайн");
    public static final OperatorEntity ADMIN_OPERATOR3 = new OperatorEntity(ADMIN_OPERATOR_ID, "МТС", "Оператор МегаФон");

    public static final List<OperatorEntity> OPERATORS = Arrays.asList(OPERATOR2, OPERATOR1);

    public static OperatorEntity getCreated() {
        return new OperatorEntity(null, "Созданный оператор", "Созданный оператор комментарий");
    }

    public static OperatorEntity getUpdated() {
        return new OperatorEntity(OPERATOR1_ID, "Обновленный оператор", "Обновленный оператор комментарий");
    }

    public static void assertMatch(OperatorEntity actual, OperatorEntity expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<OperatorEntity> actual, OperatorEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<OperatorEntity> actual, Iterable<OperatorEntity> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }
}

package ru.lab729.itpir;


import ru.lab729.itpir.model.CustomerEntity;
import ru.lab729.itpir.model.PmEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class CustomerTestData {
    public static final int CUSTOMER1_ID = START_SEQ + 30;
    public static final int ADMIN_CUSTOMER_ID = START_SEQ + 34;

    public static final CustomerEntity CUSTOMER1 = new CustomerEntity(CUSTOMER1_ID, "МТС", "Заказчик МТС");
    public static final CustomerEntity CUSTOMER2 = new CustomerEntity(CUSTOMER1_ID + 1, "ВымпелКом", "Заказчик ВымпелКом");
    public static final CustomerEntity CUSTOMER3 = new CustomerEntity(CUSTOMER1_ID + 2, "МегаФон", "Заказчик МегаФон");
    public static final CustomerEntity CUSTOMER4 = new CustomerEntity(CUSTOMER1_ID + 3, "Теле2", "Заказчик Теле2");

    public static final CustomerEntity ADMIN_CUSTOMER5 = new CustomerEntity(ADMIN_CUSTOMER_ID, "Хуавей", "Заказчик Хуавей");
    public static final CustomerEntity ADMIN_CUSTOMER6 = new CustomerEntity(ADMIN_CUSTOMER_ID + 1, "Эрикссон", "Заказчик Эрикссон");
    public static final CustomerEntity ADMIN_CUSTOMER7 = new CustomerEntity(ADMIN_CUSTOMER_ID + 2, "НСН", "Заказчик НСН");
    public static final List<CustomerEntity> CUSTOMERS = Arrays.asList(CUSTOMER2, CUSTOMER3,CUSTOMER1,CUSTOMER4);
    public static final List<CustomerEntity> ALL_CUSTOMERS = Arrays.asList(CUSTOMER2, CUSTOMER3,CUSTOMER1,ADMIN_CUSTOMER7,CUSTOMER4,ADMIN_CUSTOMER5,ADMIN_CUSTOMER6);

    public static CustomerEntity getCreated() {
        return new CustomerEntity(null, "Созданный заказчик", "Созданный заказчик комментарий");
    }

    public static CustomerEntity getUpdated() {
        return new CustomerEntity(CUSTOMER1_ID, "Обновленный заказчик", "Обновленный заказчик комментарий");
    }

    public static void assertMatch(CustomerEntity actual, CustomerEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<CustomerEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<CustomerEntity> actual, CustomerEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<CustomerEntity> actual, Iterable<CustomerEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

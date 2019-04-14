package ru.lab729.itpir;

import ru.lab729.itpir.model.ContactsAdEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lab729.itpir.SiteTestData.*;
import static ru.lab729.itpir.StatusContactTestData.STATUSCONTACT1;
import static ru.lab729.itpir.model.AbstractBaseEntity.START_SEQ;

public class ContactsAdTestData {
    public static final int CONTACTS_AD1_ID = START_SEQ + 24;
    public static final int ADMIN_CONTACTS_AD_ID = START_SEQ + 26;

    public static final ContactsAdEntity CONTACTS_AD1 = new ContactsAdEntity(CONTACTS_AD1_ID, SITE1, "Лавров", "Дмитрий",
            "Сергеевич1", "Инженер1", "8-926-637-22-19", "8-926-637-22-91",
            "bn1@mail.ru", STATUSCONTACT1, "ГИ1", false, "Москва1", "Колодезный1", "22");
    public static final ContactsAdEntity CONTACTS_AD2 = new ContactsAdEntity(CONTACTS_AD1_ID + 1, SITE2, "Иванов", "Сергей",
            "Сергеевич2", "Инженер2", "8-926-637-22-20", "8-926-637-22-92",
            "bn2@mail.ru", STATUSCONTACT1, "ГИ2", false, "Москва2", "Колодезный2", "23");
    public static final ContactsAdEntity ADMIN_CONTACTS_AD3 = new ContactsAdEntity(ADMIN_CONTACTS_AD_ID, ADMIN_SITE3, "Петров", "Иван",
            "Сергеевич3", "Инженер3", "8-926-637-22-21", "8-926-637-22-93",
            "bn3@mail.ru", STATUSCONTACT1, "ГИ3", false, "Москва3", "Колодезный2", "24");
    public static final List<ContactsAdEntity> CONTACTS_ADS = Arrays.asList(CONTACTS_AD2, CONTACTS_AD1);
    public static final List<ContactsAdEntity> ALL_CONTACTS_ADS = Arrays.asList(CONTACTS_AD2, ADMIN_CONTACTS_AD3, CONTACTS_AD1);

    public static ContactsAdEntity getCreated() {
        return new ContactsAdEntity(null, SITE1, "Фамилия", "Имя",
                "Отчество", "Должность", "8-926-637-22-21", "8-926-637-22-93",
                "bn3@mail.ru", STATUSCONTACT1, "Комментарий", false, "Город", "Улица", "Дом");
    }

    public static ContactsAdEntity getUpdated() {
        return new ContactsAdEntity(CONTACTS_AD1_ID, SITE1, "Обновленная Фамилия", "Обновленное Имя",
                "Обновленное Отчество", "Обновленная Должность", "Обновленный 8-926-637-22-21", "Обновленный 8-926-637-22-93",
                "bn3@mail.ru", STATUSCONTACT1, "Обновленный Комментарий", false, "Обновленный Город", "Обновленная Улица", "Обновленный Дом");
    }

    public static void assertMatch(ContactsAdEntity actual, ContactsAdEntity expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<ContactsAdEntity> actual, int listSize) {
        assertThat(actual).hasSize(listSize);
    }

    public static void assertMatch(Iterable<ContactsAdEntity> actual, ContactsAdEntity... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<ContactsAdEntity> actual, Iterable<ContactsAdEntity> expected) {
        //assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}

DELETE
FROM user_roles;
DELETE
FROM meals;
DELETE
FROM users;
ALTER SEQUENCE global_seq
  RESTART WITH 100000;

INSERT INTO users (name, email, password, calories_per_day)
VALUES ('User', 'user@yandex.ru', '{noop}password', 2005),
       ('Admin', 'admin@gmail.com', '{noop}admin', 1900);

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2015-05-30 10:00:00', 'Завтрак', 500, 100000),
       ('2015-05-30 13:00:00', 'Обед', 1000, 100000),
       ('2015-05-30 20:00:00', 'Ужин', 500, 100000),
       ('2015-05-31 10:00:00', 'Завтрак', 500, 100000),
       ('2015-05-31 13:00:00', 'Обед', 1000, 100000),
       ('2015-05-31 20:00:00', 'Ужин', 510, 100000),
       ('2015-06-01 14:00:00', 'Админ ланч', 510, 100001),
       ('2015-06-01 21:00:00', 'Админ ужин', 1500, 100001);

INSERT INTO operator (name, comments)
VALUES ('МТС', 'оператор МТС '),
       ('Билайн', 'оператор Билайн'),
       ('МегаФон', 'оператор МегаФон');


INSERT INTO status_activity (status)
VALUES ('Не распределен'),
       ('Приостановлен'),
       ('Отменён'),
/*       ('На исправлении ПО'),
       ('Исправлен'),
       ('Сделан запрос'),
       ('Ждем ответа'),*/
       ('В работе'),
       ('Выполнен');

INSERT INTO status_ad (status)
VALUES ('Дествующий'),
       ('Заморожен');

INSERT INTO status_executor (status)
VALUES ('Действующий'),
       ('Дисквалифицирован');

INSERT INTO status_id (status)
VALUES ('Действующие'),
       ('Кривые'),
       ('Отменены');

INSERT INTO status_os (status)
VALUES ('В работе'),
       ('Приостановлен'),
       ('Отозван'),
       ('Отменен');

INSERT INTO status_rd (status)
VALUES ('В работе'),
       ('Приостановлен'),
       ('Отозван'),
       ('Отменен');

INSERT INTO status_ssr (status)
VALUES ('В работе'),
       ('Приостановлен'),
       ('Отозван'),
       ('Отменен');

INSERT INTO status_tssr (status)
VALUES ('В работе'),
       ('Приостановлен'),
       ('Отозван'),
       ('Отменен');

INSERT INTO type_BS (BS_Type)
VALUES ('Выгородка'),
       ('Контейнер на крыше'),
       ('Контейнер на земле'),
       ('Климатический шкаф на крыше'),
       ('Indoor');

INSERT INTO type_AMS (AMS_Type)
VALUES ('Столб'),
       ('Башня'),
       ('Мачта'),
       ('Трипод'),
       ('Крыша'),
       ('Дымовая труба');

INSERT INTO type_AFS (AFS_Type)
VALUES ('Антенные опоры на фасаде'),
       ('Антенные опоры на пригрузах');

INSERT INTO executor (name, phone, email, comments, rating, status)
VALUES ('Ершов С. А.', '8-966-100-31-24', 's.e@email.com', 'АС, КМ', 10, 100020),
       ('Дамаскин М. А.', '8-966-100-31-25', 's.e@email.com', 'РС, АС, КМ', 10, 100021);

INSERT INTO ad (name, address, statusad_id)
VALUES ('ООО Будапешт', 'Москва', 100018),
       ('ООО Рога и Копыта', 'Питер', 100018),
       ('ООО Гугль', 'Питер', 100019);

INSERT INTO activity_type (activity, comments)
VALUES ('Запрос оператору', 'Уточнение'),
       ('Исправление', 'Проектирование'),
       ('Получение', 'Проектирование'),
       ('Разработка', 'Проектирование'),
       ('Оплата', 'Оплата');

INSERT INTO activity_section (section, comments)
VALUES ('ИД', 'Раздел ИД'),
       ('Обследование', 'Раздел обследование'),
       ('SSR', 'Раздел SSR'),
       ('TSSR', 'Раздел TSSR'),
       ('РД', 'Раздел РД'),
       ('ТУ', 'Раздел ТУ'),
       ('РНС', 'Раздел РНС'),
       ('Форма 1А', 'Раздел Форма 1А');


/*INSERT INTO site (site_number, site_name, operator_id, ad_id, site_date, comments)
VALUES ('1279', 'Будапешт', 100010, 100056, '2015-05-30 13:00:00', ' БС 1279'),
       ('1280', 'Будапешт2', 100010, 100057, '2015-05-30 13:00:00', ' БС 1280'),
       ('1281', 'Будапешт3', 100010, 100058, '2015-05-30 13:00:00', ' БС 1281'),
       ('1279', 'Будапешт', 100011, 100056, '2015-05-30 13:00:00', ' БС 1279'),
       ('1280', 'Будапешт2', 100012, 100056, '2015-05-30 13:00:00', ' БС 1280');*/


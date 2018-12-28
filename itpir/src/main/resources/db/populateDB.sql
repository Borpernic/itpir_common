DELETE
FROM user_roles;
DELETE
FROM meals;
DELETE
FROM users;
DELETE
FROM operator;
DELETE
FROM region;
DELETE
FROM status_contacts;
DELETE
FROM contacts_ad;


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

INSERT INTO operator (operator, comments)
VALUES ('МТС', 'оператор МТС '),
  ('Билайн', 'оператор Билайн'),
  ('МегаФон', 'оператор МегаФон');

INSERT INTO region (region, comments)
VALUES ('77', 'Москва'),
  ('50', 'МО');

INSERT INTO site (number, name, operator, region, date, city, street, building, comments)
VALUES ('1279', 'Будапешт', 100010, 100013, '2018-05-30 13:00:00', 'Москва', 'Авиамоторная', '28С', ' БС 1279'),
  ('1280', 'Будапешт2', 100010, 100013, '2018-05-30 13:00:00', 'Москва', 'Автозаводская', '18С', ' БС 1280'),
  ('1281', 'Будапешт3', 100010, 100013, '2018-05-30 13:00:00', 'Москва', 'Лесная', '38С', ' БС 1281'),
  ('1279', 'Будапешт', 100011, 100013, '2018-05-30 13:00:00', 'Москва', 'Тверская', '48С', ' БС 1279'),
  ('1280', 'Будапешт2', 100012, 100014, '2018-05-30 13:00:00', 'Москва', 'Авиамоторная', '22', ' БС 1280');

INSERT INTO status_contacts (status)
VALUES ('Нет данных'),
  ('Не звонить'),
  ('Звонить для доступа');


INSERT INTO contacts_ad (site_id, surname, name, phone1, status, confirmed)
VALUES (100015, 'Лавров', 'Дмитрий', '128656', 100020, FALSE),
  (100016, 'Иванов', 'Сергей', '356', 100020, FALSE),
  (100017, 'Петров', 'Иван', '8596455', 100020, FALSE);

INSERT INTO pm (pm, comments)
VALUES ('Мамонтов', 'МТС'),
  ('Козлов', 'Хуавей');

INSERT INTO customer (customer)
VALUES ('МТС'),
  ('ВымпелКом'),
  ('МегаФон'),
  ('Теле2'),
  ('Хуавей'),
  ('Эриксон'),
  ('НСН');

INSERT INTO project (project, pm, customer, comments)
VALUES ('МТС', 100026, 100028, 'Столб'),
  ('Хуавей', 100027, 100032, 'SWAP');

INSERT INTO curator (operator, curator)
VALUES (100010, 'Иваньков'),
  (100011, 'Сидоров'),
  (100012, 'Смирнов');

INSERT INTO band (band, comments)
VALUES ('U900', 'МТС'),
  ('U2100', 'МТС'),
  ('L2600', 'Вымпелком');

INSERT INTO status_os (status)
VALUES ('В работе'),
  ('Заморожен'),
  ('Отозван'),
  ('Отменен');

INSERT INTO type_os (type)
VALUES ('Новая стройка'),
  ('Модернизация'),
  ('Indoor'),
  ('WLL'),
  ('РРС');
INSERT INTO type_BS (type)
VALUES ('Выгородка'),
  ('Контейнер на крыше'),
  ('Контейнер на земле'),
  ('Климатический шкаф на крыше'),
  ('Оборудование в серверной АД');

INSERT INTO type_AMS (type)
VALUES ('Столб'),
  ('Башня'),
  ('Мачта'),
  ('Трипод'),
  ('Крыша'),
  ('Дымовая труба');

INSERT INTO type_AFS (type)
VALUES ('Антенные опоры на фасаде'),
  ('Антенные опоры на пригрузах');

INSERT INTO internal_number (number, project)
VALUES ('3568999', 100035),
  ('3569997', 100036);

INSERT INTO os (date, site, internal_number, curator, band, type_os, type_BS, type_AMS, type_AFS, status_os, comments)
VALUES (now(), 100015, 100065, 100037, 100040, 100047, 100052, 100058, 100063, 100043, 'МТС'),
  (now(), 100018, 100066, 100038, 100040, 100047, 100052, 100058, 100063, 100043, 'HW');

INSERT INTO nomenclature_works (works)
VALUES ('Обследование ОС'),
  ('Разработка SSR'),
  ('Разработка TSSR'),
  ('Разработка ТЗ'),
  ('Разработка ПЗ'),
  ('Разработка РС'),
  ('Разработка ЭМ'),
  ('Разработка ЭС'),
  ('Разработка АС'),
  ('Разработка КМ'),
  ('Разработка РРС'),
  ('Разработка РРС1'),
  ('Разработка ДП'),
  ('Разработка ПРА');

INSERT INTO type_implementer (type)
VALUES ('Оклад'),
  ('Сделка'),
  ('Фриланс');

INSERT INTO status_implementer (status)
VALUES ('Готов к работе'),
  ('Занят'),
  ('Дисквалифицирован');

INSERT INTO implementer (implementer, phone, email, type, status, rating, comments)
VALUES ('Ершов С. А.', '8-966-100-31-24', 's.e@email.com', 100083, 100086, 1, 'АС, КМ'),
  ('Дамаскин М. А.', '8-966-100-31-25', 'm.d@email.com', 100084, 100087, 1, 'РС, АС, КМ');

INSERT INTO tzp (tzp, price, type_os, type_implementer, comments)
VALUES ('Альбом РС', 1200, 100047, 100083, 'МТС'),
  ('SSR', 3200, 100048, 100084, 'МТС');

INSERT INTO status_activity (status)
VALUES ('Нет подтверждения'),
  ('В работе'),
  ('Выполнено'),
  ('Приостановлено'),
  ('Отозвано');

INSERT INTO type_activity (type, source_data, source_RD, RNS, F1A, survey, SSR, TSSR, RD, implDoc)
VALUES ('Запрос исходных данных', TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE),
  ('Запрос исходного проекта', FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE),
  ('Уточнение исходных данных', FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE),
  ('Обследование', FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE),
  ('Фотоотчет', FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE),
  ('SSR', FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE),
  ('TSSR', FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE),
  ('РД', FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE),
  ('Сдача РД', FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE),
  ('ИД', FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE);


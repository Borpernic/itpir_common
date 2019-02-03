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

INSERT INTO operator (operator, comments, user_id)
VALUES ('МТС', 'Оператор МТС', 100000),
       ('Билайн', 'Оператор Билайн', 100000),
       ('МегаФон', 'Оператор МегаФон', 100001);

INSERT INTO region (region, comments, user_id)
VALUES ('77', 'Москва', 100001),
       ('50', 'МО', 100001);

INSERT INTO site (number, name, operator, region, date_time, city, street, building, comments, user_id)
VALUES ('1279', 'Будапешт', 100010, 100013, '2018-05-30 13:00:00', 'Москва', 'Авиамоторная', '28С', ' БС 1279', 100001),
       ('1280',
        'Будапешт2',
        100010,
        100013,
        '2018-05-30 13:00:00',
        'Москва',
        'Автозаводская',
        '18С',
        ' БС 1280',
        100001),
       ('1281', 'Будапешт3', 100010, 100013, '2018-05-30 13:00:00', 'Москва', 'Лесная', '38С', ' БС 1281', 100001),
       ('1279', 'Будапешт', 100011, 100013, '2018-05-30 13:00:00', 'Москва', 'Тверская', '48С', ' БС 1279', 100001),
       ('1280', 'Будапешт2', 100012, 100014, '2018-05-30 13:00:00', 'Москва', 'Авиамоторная', '22', ' БС 1280', 100001);

INSERT INTO status_contacts (status, user_id)
VALUES ('Нет данных', 100001),
       ('Не звонить', 100001),
       ('Звонить для доступа', 100001);


INSERT INTO contacts_ad (site_id, surname, name, phone1, status, confirmed, user_id)
VALUES (100015, 'Лавров', 'Дмитрий', '128656', 100020, FALSE, 100001),
       (100016, 'Иванов', 'Сергей', '356', 100020, FALSE, 100001),
       (100017, 'Петров', 'Иван', '8596455', 100020, FALSE, 100001);

INSERT INTO pm (pm, comments, user_id)
VALUES ('Мамонтов', 'МТС', 100001),
       ('Козлов', 'Хуавей', 100001);

INSERT INTO customer (customer, user_id)
VALUES ('МТС', 100001),
       ('ВымпелКом', 100001),
       ('МегаФон', 100001),
       ('Теле2', 100001),
       ('Хуавей', 100001),
       ('Эриксон', 100001),
       ('НСН', 100001);

INSERT INTO project (project, pm, customer, comments, user_id)
VALUES ('МТС', 100026, 100028, 'Столб', 100001),
       ('Хуавей', 100027, 100032, 'SWAP', 100001);

INSERT INTO curator (operator, curator, user_id)
VALUES (100010, 'Иваньков', 100001),
       (100011, 'Сидоров', 100001),
       (100012, 'Смирнов', 100001);

INSERT INTO band (band, comments, user_id)
VALUES ('U900', 'МТС', 100001),
       ('U2100', 'МТС', 100001),
       ('L2600', 'Вымпелком', 100001);

INSERT INTO status_os (status, user_id)
VALUES ('В работе', 100001),
       ('Заморожен', 100001),
       ('Отозван', 100001),
       ('Отменен', 100001);

INSERT INTO type_os (type, user_id)
VALUES ('Новая стройка', 100001),
       ('Модернизация', 100001),
       ('Indoor', 100001),
       ('WLL', 100001),
       ('РРС', 100001);
INSERT INTO type_BS (type, user_id)
VALUES ('Выгородка', 100001),
       ('Контейнер на крыше', 100001),
       ('Контейнер на земле', 100001),
       ('Климатический шкаф на крыше', 100001),
       ('Оборудование в серверной АД', 100001);

INSERT INTO type_AMS (type, user_id)
VALUES ('Столб', 100001),
       ('Башня', 100001),
       ('Мачта', 100001),
       ('Трипод', 100001),
       ('Крыша', 100001),
       ('Дымовая труба', 100001);

INSERT INTO type_AFS (type, user_id)
VALUES ('Антенные опоры на фасаде', 100001),
       ('Антенные опоры на пригрузах', 100001);

INSERT INTO internal_number (number, project, user_id)
VALUES ('3568999', 100035, 100001),
       ('3569997', 100036, 100001);

INSERT INTO os (date_time,
                site,
                internal_number,
                curator,
                band,
                type_os,
                type_BS,
                type_AMS,
                type_AFS,
                status_os,
                comments,
                user_id)
VALUES (now(), 100015, 100065, 100037, 100040, 100047, 100052, 100058, 100063, 100043, 'МТС', 100001),
       (now(), 100018, 100066, 100038, 100040, 100047, 100052, 100058, 100063, 100043, 'HW', 100001);

INSERT INTO nomenclature_works (works, user_id)
VALUES ('Обследование ОС', 100001),
       ('Разработка SSR', 100001),
       ('Разработка TSSR', 100001),
       ('Разработка ТЗ', 100001),
       ('Разработка ПЗ', 100001),
       ('Разработка РС', 100001),
       ('Разработка ЭМ', 100001),
       ('Разработка ЭС', 100001),
       ('Разработка АС', 100001),
       ('Разработка КМ', 100001),
       ('Разработка РРС', 100001),
       ('Разработка РРС1', 100001),
       ('Разработка ДП', 100001),
       ('Разработка ПРА', 100001);

INSERT INTO type_implementer (type, user_id)
VALUES ('Оклад', 100001),
       ('Сделка', 100001),
       ('Фриланс', 100001);

INSERT INTO status_implementer (status, user_id)
VALUES ('Готов к работе', 100001),
       ('Занят', 100001),
       ('Дисквалифицирован', 100001);

INSERT INTO implementer (implementer, phone, email, type, status, rating, comments, user_id)
VALUES ('Ершов С. А.', '8-966-100-31-24', 's.e@email.com', 100083, 100086, 1, 'АС, КМ', 100001),
       ('Дамаскин М. А.', '8-966-100-31-25', 'm.d@email.com', 100084, 100087, 1, 'РС, АС, КМ', 100001);

INSERT INTO tzp (tzp, razmernost, price, type_os, type_implementer, comments, user_id)
VALUES ('1. Обследование площадки, сбор исходных данных, составление и согласование Акта обследования с Заказчиком при модернизации существующего объекта',
        'акт',
        4800,
        100048,
        100084,
        'Модернизация',
        100001),
       ('1.1. Обследование площадки, сбор исходных данных (модернизация БС).',
        'шт.',
        1400,
        100048,
        100084,
        'Модернизация',
        100001),
       ('1.2. Подготовка отчета по обследованию площадки SSR', 'шт.', 1300, 100048, 100084, 'Модернизация', 100001),
       ('1.3. Составление и согласование Акта обследования с Заказчиком при модернизации существующего объекта',
        'акт',
        2100,
        100048,
        100084,
        'Модернизация',
        100001),
       ('2. Обследование площадки, сбор исходных данных, составление и согласование Акта обследования с (новое строительство БС).',
        'альбом',
        4800,
        100047,
        100084,
        'Новая стройка',
        100001),
       ('2.1. Обследование площадки, сбор исходных данных (новое строительство БС).',
        'шт.',
        1600,
        100047,
        100084,
        'Новая стройка',
        100001),
       ('2.2. Составление и согласование Акта обследования с Заказчиком для новой стройки',
        'шт.',
        3200,
        100047,
        100084,
        'Новая стройка',
        100001),
       ('3. Разработка основного комплекта рабочих чертежей марки АС при выполнении работ по модернизации',
        'альбом',
        1400,
        100048,
        100084,
        'Модернизация',
        100001),
       ('4. Разработка основного комплекта рабочих чертежей марки РС при модернизации БС',
        'альбом',
        1400,
        100048,
        100084,
        'Модернизация',
        100001),
       ('5. Разработка основного комплекта рабочих чертежей марки ЭМ при выполнении работ по модернизации БС',
        'альбом',
        1400,
        100048,
        100084,
        'Модернизация',
        100001),
       ('6. Разработка основного комплекта рабочих чертежей марки РРС',
        'альбом',
        1400,
        100048,
        100084,
        'Модернизация',
        100001),
       ('7. Разработка основного комплекта рабочих чертежей марки РРС1',
        'альбом',
        1400,
        100048,
        100084,
        'Модернизация',
        100001),
       ('8. Разработка основного комплекта рабочих чертежей марки ЭС (включая ТУ на электроснабжение + акт разграничения БП)',
        'альбом',
        1400,
        100047,
        100084,
        'Новая стройка',
        100001),
       ('9. Разработка основного комплекта рабочих чертежей марки КМ',
        'альбом',
        1400,
        100047,
        100084,
        'Новая стройка',
        100001),
       ('10. Разработка основного комплекта рабочих чертежей марки КЖ',
        'альбом',
        1400,
        100047,
        100084,
        'Новая стройка',
        100001),
       ('11. Разработка основного комплекта рабочих чертежей марки МЗ',
        'альбом',
        1400,
        100047,
        100084,
        'Новая стройка',
        100001),
       ('12. Разработка основного комплекта рабочих чертежей марки ОВ',
        'альбом',
        1400,
        100047,
        100084,
        'Новая стройка',
        100001),
       ('13. Разработка основного комплекта рабочих чертежей марки ООС',
        'альбом',
        1400,
        100047,
        100084,
        'Новая стройка',
        100001),
       ('14. Марка ТХ (Технологические решения)', 'альбом', 1400, 100047, 100084, 'Новая стройка', 100001),
       ('15. Дополнительные проектные работы', 'шт.', 500, 100047, 100084, 'Новая стройка', 100001),
       ('16. ТУ на электроснабжение + акт разграничения БП', 'комплект', 500, 100047, 100084, 'Новая стройка', 100001),
       ('17. Согласование ПТР с АД', 'шт.', 500, 100047, 100084, 'Новая стройка', 100001),
       ('18. Обследование площадки, сбор исходных данных. Интервал РРС. (2 БС) WLL',
        'шт.',
        2100,
        100047,
        100084,
        'Новая стройка',
        100001),
       ('19. Подготовка отчета по радио разведки. ПРА. WLL', 'шт.', 1000, 100047, 100084, 'Новая стройка', 100001);

INSERT INTO type_task (type, user_id)
VALUES ('Запросить информацию', 100001),
       ('Получен заказ от PM', 100001),
       ('Выдать в работу исполнителю', 100001),
       ('Проектирование/разработка', 100001),
       ('Отправка заказчику', 100001),
       ('Отправка исполнителю', 100001),
       ('Отправка оператору', 100001),
       ('Отправка РРС', 100001),
       ('Отправка ВЭП', 100001),
       ('Отправка ПЭП', 100001),
       ('Отправка СМР', 100001),
       ('Отправка АД', 100001),
       ('Получение замечаний', 100001),
       ('Исправление замечаний', 100001),
       ('Подготовить документы в бумаге', 100001),
       ('Передать документы в бумаге', 100001),
       ('Оплатить', 100001),
       ('Написать письмо', 100001),
       ('Позвонить', 100001),
       ('Встретиться', 100001);

INSERT INTO result_task (result, user_id)
VALUES ('Отправлено письмо', 100001),
       ('Заказ обработан', 100001),
       ('Исходные данные отправлены исполнителю', 100001),
       ('Подготовлен запрос исходных данных', 100001),
       ('Подготовлен запрос исходного проекта', 100001),
       ('Получены документы', 100001),
       ('Замечания исправлены', 100001),
       ('Документы отправлены заказчику', 100001),
       ('Документы отправлены оператору', 100001),
       ('Документы отправлены АД', 100001),
       ('Документы отправлены заказчику после исправления', 100001),
       ('Документы отправлены оператору после исправления', 100001),
       ('Документы отправлены АД после исправления', 100001),
       ('Документы подготовлены', 100001),
       ('Документы переданы', 100001),
       ('Оплачено', 100001),
       ('Письмо отправлено', 100001),
       ('Звонок выполнен', 100001),
       ('Встреча проведена', 100001),
       ('Работы перенесены', 100001),
       ('Работы отменены', 100001);

INSERT INTO department (department, user_id)
VALUES ('Проектировщик', 100001),
       ('ГИП', 100001),
       ('PM', 100001),
       ('АД', 100001),
       ('Куратор', 100001),
       ('РРС', 100001),
       ('ВЭП', 100001),
       ('ПЭП', 100001),
       ('СМР', 100001),
       ('Оператор', 100001),
       ('Заказчик', 100001),
       ('Договорник', 100001);

INSERT INTO status_activity (status, user_id)
VALUES ('Нет подтверждения', 100001),
       ('В работе', 100001),
       ('Выполнено', 100001),
       ('Приостановлено', 100001),
       ('Отозвано', 100001);

INSERT INTO type_activity (type, source_data, source_RD, RNS, F1A, survey, SSR, TSSR, RD, implDoc, signedLL, user_id)
VALUES ('Запрос исходных данных', TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, 100001),
       ('Запрос исходного проекта', FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, 100001),
       ('Уточнение исходных данных', FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, 100001),
       ('Обследование', FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, 100001),
       ('Фотоотчет', FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, 100001),
       ('SSR', FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, 100001),
       ('TSSR', FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, 100001),
       ('РД', FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, 100001),
       ('Сдача РД', FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, 100001),
       ('ИД', FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, 100001),
       ('Согласование с АД', FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, 100001);

INSERT INTO activity (os, implementer, type_activity, date_time, plane_date_time, rating, status_activity, user_id)
VALUES (100067, 100089, 100178, now(), now(), 1, 100169, 100001),
       (100068, 100089, 100179, now(), now(), 1, 100169, 100001);

INSERT INTO date_change_status (activity, date_time, status_activity, user_id)
VALUES (100184, now(), 100168, 100001),
       (100185, now(), 100168, 100001);

INSERT INTO task (activity,
                  date_time,
                  type_task,
                  department,
                  plane_date_time,
                  right_on_time,
                  approve,
                  approve_date_time,
                  result_task,
                  user_id)
VALUES (100184, now(), 100115, 100157, now(), TRUE, TRUE, now(), 100140, 100001),
       (100184, now(), 100115, 100157, now(), TRUE, TRUE, now(), 100140, 100001),
       (100184, now(), 100115, 100157, now(), TRUE, TRUE, now(), 100140, 100001),
       (100184, now(), 100115, 100157, now(), TRUE, TRUE, now(), 100140, 100001);

INSERT INTO comments (os, implementer, date_time, comments, user_id)
VALUES (100067, 100089, now(), 'АД просит увеличения арендной платы.', 100001),
       (100068, 100089, now(), 'Закзчик просит связаться с АД.', 100001);


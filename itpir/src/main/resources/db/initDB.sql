DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS operator CASCADE;
DROP TABLE IF EXISTS status_ad CASCADE;
DROP TABLE IF EXISTS ad CASCADE;
DROP TABLE IF EXISTS Site CASCADE;
DROP TABLE IF EXISTS status_activity CASCADE;
DROP TABLE IF EXISTS status_executor CASCADE;
DROP TABLE IF EXISTS status_id CASCADE;
DROP TABLE IF EXISTS status_os CASCADE;
DROP TABLE IF EXISTS status_rd CASCADE;
DROP TABLE IF EXISTS status_ssr CASCADE;
DROP TABLE IF EXISTS status_tssr CASCADE;
DROP TABLE IF EXISTS type_BS CASCADE;
DROP TABLE IF EXISTS type_AMS CASCADE;
DROP TABLE IF EXISTS type_AFS CASCADE;
DROP TABLE IF EXISTS executor CASCADE;
DROP TABLE IF EXISTS activity_type CASCADE;
DROP TABLE IF EXISTS activity_section CASCADE;


DROP SEQUENCE IF EXISTS global_seq CASCADE;
CREATE SEQUENCE global_seq
  START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  calories_per_day INTEGER DEFAULT 2000    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE meals (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id     INTEGER   NOT NULL,
  date_time   TIMESTAMP NOT NULL,
  description TEXT      NOT NULL,
  calories    INT       NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX meals_unique_user_datetime_idx
  ON meals (user_id, date_time);

CREATE TABLE operator
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name     TEXT NOT NULL,
  comments TEXT NOT NULL,
  CONSTRAINT operator_name_idx UNIQUE (name)

);

CREATE TABLE status_ad (
  id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  status TEXT NOT NULL,
  CONSTRAINT status_ad_status_idx UNIQUE (status)

);


CREATE TABLE ad
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        TEXT    NOT NULL,
  address     TEXT    NOT NULL,
  statusAd_Id INTEGER NOT NULL,
  FOREIGN KEY (statusAd_Id) REFERENCES status_ad (id) ON DELETE CASCADE,
  CONSTRAINT ad_name_idx UNIQUE (name)

);


CREATE TABLE Site
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  site_number VARCHAR   NOT NULL,
  site_name   TEXT      NOT NULL,
  operator_id INTEGER   NOT NULL,
  ad_Id       INTEGER   NOT NULL,
  site_date   TIMESTAMP NOT NULL,
  comments    TEXT      NOT NULL,
  FOREIGN KEY (operator_id) REFERENCES operator (id) ON DELETE CASCADE,
  FOREIGN KEY (ad_Id) REFERENCES ad (id) ON DELETE CASCADE

);
CREATE UNIQUE INDEX site_site_number_operator_id_key
  ON Site (site_number, operator_id);

CREATE TABLE status_activity
(
  id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  status TEXT NOT NULL,
  CONSTRAINT status_activity_status_idx UNIQUE (status)

);
CREATE TABLE status_executor
(
  id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  status TEXT NOT NULL,
  CONSTRAINT status_executor_status_idx UNIQUE (status)

);
CREATE TABLE status_id
(
  id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  status TEXT NOT NULL,
  CONSTRAINT status_id_status_idx UNIQUE (status)

);

CREATE TABLE status_os
(
  id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  status TEXT NOT NULL,
  CONSTRAINT status_os_status_idx UNIQUE (status)

);
CREATE TABLE status_rd
(
  id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  status TEXT NOT NULL,
  CONSTRAINT status_rd_status_idx UNIQUE (status)

);
CREATE TABLE status_ssr
(
  id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  status TEXT NOT NULL,
  CONSTRAINT status_ssr_status_idx UNIQUE (status)

);
CREATE TABLE status_tssr
(
  id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  status TEXT NOT NULL,
  CONSTRAINT status_tssr_status_idx UNIQUE (status)
);
CREATE TABLE type_BS
(
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  BS_Type TEXT NOT NULL,
  CONSTRAINT type_BS_bsType_idx UNIQUE (BS_Type)

);
CREATE TABLE type_AMS
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  AMS_Type TEXT NOT NULL,
  CONSTRAINT type_AMS_amsType_idx UNIQUE (AMS_Type)

);
CREATE TABLE type_AFS
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  AFS_Type TEXT NOT NULL,
  CONSTRAINT type_AFS_afsType_idx UNIQUE (AFS_Type)

);
CREATE TABLE executor
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name     TEXT    NOT NULL,
  phone    TEXT,
  email    TEXT,
  comments TEXT,
  rating   INTEGER NOT NULL,
  status   INTEGER NOT NULL,
  CONSTRAINT executor_name_idx UNIQUE (name),
  FOREIGN KEY (status) REFERENCES status_executor (id) ON DELETE CASCADE
);
CREATE TABLE activity_type
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  activity TEXT NOT NULL,
  comments TEXT,
  CONSTRAINT activity_type_activity_idx UNIQUE (activity)
);
CREATE TABLE activity_section
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  section TEXT NOT NULL,
  comments TEXT,
  CONSTRAINT activity_section_section_idx UNIQUE (section)
);




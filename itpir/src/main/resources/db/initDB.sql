DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS operator CASCADE;
DROP TABLE IF EXISTS region CASCADE;
DROP TABLE IF EXISTS site CASCADE;
DROP TABLE IF EXISTS status_contacts CASCADE;
DROP TABLE IF EXISTS contacts_ad CASCADE;
DROP TABLE IF EXISTS pm CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS project CASCADE;
DROP TABLE IF EXISTS curator CASCADE;
DROP TABLE IF EXISTS band CASCADE;
DROP TABLE IF EXISTS status_os CASCADE;
DROP TABLE IF EXISTS type_os CASCADE;
DROP TABLE IF EXISTS type_BS CASCADE;
DROP TABLE IF EXISTS type_AMS CASCADE;
DROP TABLE IF EXISTS type_AFS CASCADE;
DROP TABLE IF EXISTS internal_number CASCADE;
DROP TABLE IF EXISTS os CASCADE;
DROP TABLE IF EXISTS nomenclature_works CASCADE;
DROP TABLE IF EXISTS type_implementer CASCADE;
DROP TABLE IF EXISTS status_implementer CASCADE;
DROP TABLE IF EXISTS implementer CASCADE;
DROP TABLE IF EXISTS tzp CASCADE;
DROP TABLE IF EXISTS type_task CASCADE;
DROP TABLE IF EXISTS result_task CASCADE;
DROP TABLE IF EXISTS department CASCADE;
DROP TABLE IF EXISTS status_activity CASCADE;
DROP TABLE IF EXISTS type_activity CASCADE;
DROP TABLE IF EXISTS activity CASCADE;
DROP TABLE IF EXISTS date_change_status CASCADE;
DROP TABLE IF EXISTS task CASCADE;

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
  operator TEXT NOT NULL,
  comments TEXT NOT NULL,
  CONSTRAINT operator_operator_idx UNIQUE (operator)

);

CREATE TABLE region
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  region   TEXT NOT NULL,
  comments TEXT NOT NULL,
  CONSTRAINT region_region_idx UNIQUE (region)
);

CREATE TABLE site
(
  id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  number    VARCHAR                 NOT NULL,
  name      TEXT                    NOT NULL,
  operator  INTEGER                 NOT NULL,
  region    INTEGER                 NOT NULL,
  date_time TIMESTAMP DEFAULT now() NOT NULL,
  city      TEXT                    NOT NULL,
  street    TEXT                    NOT NULL,
  building  TEXT                    NOT NULL,
  comments  TEXT                    NOT NULL,
  FOREIGN KEY (operator) REFERENCES operator (id),
  FOREIGN KEY (region) REFERENCES region (id)
);
CREATE UNIQUE INDEX site_site_number_name_operator_id_key
  ON Site (number, name, operator);

CREATE TABLE status_contacts
(
  id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  status TEXT NOT NULL,
  CONSTRAINT status_contacts_status_idx UNIQUE (status)
);

CREATE TABLE contacts_ad
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  site_id    INTEGER NOT NULL,
  surname    TEXT    NOT NULL,
  name       TEXT    NOT NULL,
  middlename TEXT,
  position   TEXT,
  phone1     TEXT    NOT NULL,
  phone2     TEXT,
  email      TEXT,
  status     INTEGER NOT NULL,
  comments   TEXT,
  confirmed  BOOLEAN NOT NULL,
  city       TEXT,
  street     TEXT,
  building   TEXT,

  FOREIGN KEY (site_id) REFERENCES site (id) ON DELETE CASCADE,
  FOREIGN KEY (status) REFERENCES status_contacts (id),
  CONSTRAINT contacts_ad_email_idx UNIQUE (email)
);

CREATE TABLE pm
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  pm       TEXT NOT NULL,
  comments TEXT,
  CONSTRAINT pm_pm_idx UNIQUE (pm)
);

CREATE TABLE customer
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  customer VARCHAR NOT NULL,
  comments TEXT,
  CONSTRAINT customer_customer_idx UNIQUE (customer)
);

CREATE TABLE project
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  project  VARCHAR NOT NULL,
  pm       INTEGER NOT NULL,
  customer INTEGER NOT NULL,
  comments TEXT    NOT NULL,
  FOREIGN KEY (pm) REFERENCES pm (id),
  FOREIGN KEY (customer) REFERENCES customer (id),
  CONSTRAINT project_project_idx UNIQUE (project)

);

CREATE TABLE curator
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  operator INTEGER NOT NULL,
  curator  TEXT    NOT NULL,
  phone    TEXT,
  email    TEXT,
  comments TEXT,
  CONSTRAINT curator_curator_idx UNIQUE (curator),
  FOREIGN KEY (operator) REFERENCES operator (id)
);

CREATE TABLE band
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  band     TEXT NOT NULL,
  comments TEXT,
  CONSTRAINT band_band_idx UNIQUE (band)
);

CREATE TABLE status_os
(
  id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  status TEXT NOT NULL,
  CONSTRAINT status_os_status_idx UNIQUE (status)
);

CREATE TABLE type_os
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  type TEXT NOT NULL,
  CONSTRAINT type_os_type_idx UNIQUE (type)
);

CREATE TABLE type_BS
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  type TEXT NOT NULL,
  CONSTRAINT type_BS_type_idx UNIQUE (type)
);

CREATE TABLE type_AMS
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  type TEXT NOT NULL,
  CONSTRAINT type_AMS_type_idx UNIQUE (type)
);

CREATE TABLE type_AFS
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  type TEXT NOT NULL,
  CONSTRAINT type_AFS_type_idx UNIQUE (type)
);

CREATE TABLE internal_number
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  project  INTEGER NOT NULL,
  number   TEXT    NOT NULL,
  comments TEXT,
  CONSTRAINT internal_number_number_idx UNIQUE (number),
  FOREIGN KEY (project) REFERENCES project (id)
);

CREATE TABLE os
(
  id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date_time       TIMESTAMP DEFAULT now() NOT NULL,
  site            INTEGER                 NOT NULL,
  internal_number INTEGER                 NOT NULL,
  curator         INTEGER                 NOT NULL,
  band            INTEGER                 NOT NULL,
  type_os         INTEGER                 NOT NULL,
  type_BS         INTEGER                 NOT NULL,
  type_AMS        INTEGER                 NOT NULL,
  type_AFS        INTEGER                 NOT NULL,
  source_data     BOOLEAN             DEFAULT FALSE,
  source_RD       BOOLEAN             DEFAULT FALSE,
  RNS             BOOLEAN             DEFAULT FALSE,
  F1A             BOOLEAN             DEFAULT FALSE,
  survey          BOOLEAN             DEFAULT FALSE,
  SSR             BOOLEAN             DEFAULT FALSE,
  TSSR            BOOLEAN             DEFAULT FALSE,
  RD              BOOLEAN             DEFAULT FALSE,
  implDoc         BOOLEAN             DEFAULT FALSE,
  signedLL        BOOLEAN             DEFAULT FALSE,
  status_os       INTEGER                 NOT NULL,
  comments        TEXT                    NOT NULL,
  FOREIGN KEY (site) REFERENCES site (id),
  FOREIGN KEY (internal_number) REFERENCES internal_number (id),
  FOREIGN KEY (curator) REFERENCES curator (id),
  FOREIGN KEY (band) REFERENCES band (id),
  FOREIGN KEY (type_os) REFERENCES type_os (id),
  FOREIGN KEY (type_BS) REFERENCES type_BS (id),
  FOREIGN KEY (type_AMS) REFERENCES type_AMS (id),
  FOREIGN KEY (type_AFS) REFERENCES type_AFS (id),
  FOREIGN KEY (status_os) REFERENCES status_os (id)
);

CREATE UNIQUE INDEX os_site_internal_number_id_key
  ON os (site, internal_number);

CREATE TABLE nomenclature_works
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  works    TEXT NOT NULL,
  comments TEXT,
  CONSTRAINT nomenclature_works_works_idx UNIQUE (works)
);

CREATE TABLE type_implementer
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  type     TEXT NOT NULL,
  comments TEXT,
  CONSTRAINT type_implementer_type_idx UNIQUE (type)
);

CREATE TABLE status_implementer
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  status   TEXT NOT NULL,
  comments TEXT,
  CONSTRAINT status_implementer_status_idx UNIQUE (status)
);

CREATE TABLE implementer
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  implementer TEXT    NOT NULL,
  phone       TEXT    NOT NULL,
  email       TEXT    NOT NULL,
  status      INTEGER NOT NULL,
  type        INTEGER NOT NULL,
  rating      NUMERIC NOT NULL,
  comments    TEXT,
  CONSTRAINT implementer_implementer_idx UNIQUE (implementer),
  FOREIGN KEY (status) REFERENCES status_implementer (id),
  FOREIGN KEY (type) REFERENCES type_implementer (id)
);

CREATE TABLE tzp
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  tzp              TEXT    NOT NULL,
  razmernost       TEXT    NOT NULL,
  price            NUMERIC NOT NULL,
  type_os          INTEGER NOT NULL,
  type_implementer INTEGER NOT NULL,

  comments         TEXT,
  CONSTRAINT tzp_tzp_idx UNIQUE (tzp)
);

CREATE TABLE type_task
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  type     TEXT NOT NULL,
  comments TEXT,
  CONSTRAINT type_task_type_idx UNIQUE (type)
);

CREATE TABLE result_task
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  result   TEXT NOT NULL,
  comments TEXT,
  CONSTRAINT result_task_result_idx UNIQUE (result)
);

CREATE TABLE department
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  department TEXT NOT NULL,
  comments   TEXT,
  CONSTRAINT department_department_idx UNIQUE (department)
);

CREATE TABLE status_activity
(
  id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  status TEXT NOT NULL,
  CONSTRAINT status_activity_status_idx UNIQUE (status)
);


CREATE TABLE type_activity
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  type        TEXT    NOT NULL,
  source_data BOOLEAN NOT NULL,
  source_RD   BOOLEAN NOT NULL,
  RNS         BOOLEAN NOT NULL,
  F1A         BOOLEAN NOT NULL,
  survey      BOOLEAN NOT NULL,
  SSR         BOOLEAN NOT NULL,
  TSSR        BOOLEAN NOT NULL,
  RD          BOOLEAN NOT NULL,
  implDoc     BOOLEAN NOT NULL,
  signedLL    BOOLEAN NOT NULL,
  comments    TEXT,
  CONSTRAINT type_activity_type_idx UNIQUE (type)
);

CREATE TABLE activity
(
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  os                      INTEGER                 NOT NULL,
  implementer             INTEGER                 NOT NULL,
  type_activity           INTEGER                 NOT NULL,
  date_time               TIMESTAMP DEFAULT now() NOT NULL,
  plane_date_time         TIMESTAMP               NOT NULL,
  rating                  NUMERIC                 NOT NULL,
  accept                  BOOLEAN,
  accept_date_time        TIMESTAMP,
  status_activity         INTEGER                 NOT NULL,
  date_time_change_status TIMESTAMP,
  comments                TEXT,
  CONSTRAINT activity_idx UNIQUE (os, type_activity, implementer),
  FOREIGN KEY (os) REFERENCES os (id),
  FOREIGN KEY (implementer) REFERENCES implementer (id),
  FOREIGN KEY (type_activity) REFERENCES type_activity (id),
  FOREIGN KEY (status_activity) REFERENCES status_activity (id)
);

CREATE TABLE date_change_status
(
  id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  activity        INTEGER   NOT NULL,
  date_time       TIMESTAMP NOT NULL,
  status_activity INTEGER   NOT NULL,
  comments        TEXT,
  FOREIGN KEY (activity) REFERENCES activity (id),
  FOREIGN KEY (status_activity) REFERENCES status_activity (id)
);

CREATE TABLE task
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  activity          INTEGER                 NOT NULL,
  date_time         TIMESTAMP DEFAULT now() NOT NULL,
  type_task         INTEGER                 NOT NULL,
  department        INTEGER                 NOT NULL,
  plane_date_time   TIMESTAMP               NOT NULL,
  right_on_time     BOOLEAN,
  approve           BOOLEAN,
  approve_date_time TIMESTAMP,
  result_task       INTEGER                 NOT NULL,
  comments          TEXT,
  /*CONSTRAINT task_activity_type_task_department_idx UNIQUE (activity, type_task, department),*/
  FOREIGN KEY (activity) REFERENCES activity (id),
  FOREIGN KEY (type_task) REFERENCES type_task (id),
  FOREIGN KEY (department) REFERENCES department (id),
  FOREIGN KEY (result_task) REFERENCES result_task (id)
);
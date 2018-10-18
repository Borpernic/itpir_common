DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS Site;

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

CREATE TABLE Site
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  site_number VARCHAR NOT NULL,
  site_name TEXT      NOT NULL,
  operator_id     INTEGER   NOT NULL,
  ad_Id     INTEGER   NOT NULL,
  site_date   TIMESTAMP NOT NULL,
  comments TEXT      NOT NULL,
  FOREIGN KEY (operator_id) REFERENCES operator (id) ON DELETE CASCADE,
  FOREIGN KEY (ad_Id) REFERENCES ad (id) ON DELETE CASCADE

);

CREATE UNIQUE INDEX site_site_number_operator_id_key
  ON Site (site_number, operator_id);


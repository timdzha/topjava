DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id) VALUES
  ('27/02/2019 10:00:00', 'Завтрак', 1000, 100000),
  ('27/02/2019 13:00:00', 'Обед', 500, 100000),
  ('27/02/2019 20:00:00', 'Ужин', 500, 100000),
  ('28/02/2019 10:00:00', 'Завтрак', 500, 100000),
  ('28/02/2019 13:00:00', 'Обед', 1000, 100000),
  ('28/02/2019 20:00:00', 'Ужин', 1000, 100000),
  ('28/01/2019 15:00:00', 'Обед', 1000, 100001),
  ('28/01/2019 20:00:00', 'Ужин', 1000, 100001);

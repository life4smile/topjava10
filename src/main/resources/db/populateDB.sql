DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, id, datetime, description, calories) VALUES
 (100001, 1, '2015-05-30 14:00', 'Админ ланч', 510),
 (100001, 2, '2015-05-30 21:00', 'Админ ужин', 510),
 (100000, 3, '2015-05-31 10:00', 'Завтрак', 500),
 (100000, 4, '2015-05-31 14:00', 'Обед', 1000),
 (100000, 5, '2015-05-31 18:00', 'Ужин', 500);

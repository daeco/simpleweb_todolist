INSERT INTO todo (todo_id, contents, created_datetime, reference) VALUES
(1, '집안일', '2020-01-01 13:00:00', null),
(2, '빨래', '2020-01-01 15:00:00', '1'),
(3, '청소', '2020-03-01 13:00:00', '1'),
(4, '방청소', '2020-05-01 13:00:00', '1, 3');
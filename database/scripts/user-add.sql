insert into rify_user (account, password, email, status, role, permissions, create_by, create_time, update_by, update_time)
values ('admin',
        '$2a$10$CiqxrOugoB/lk6nk/jur0Oc91s6g8Sdqu3kYN1Ff/jldbeOXymsN2',
        'admin@gmail.com',
        'ENABLED',
        'admin',
        '{ admin, user, system }',
        'admin',
        '2020-01-01 00:00:00',
        'admin',
        now());

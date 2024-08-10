drop table if exists rify_user;
drop type rify_user_status;

create type rify_user_status as enum ('ENABLED', 'DISABLED', 'DELETE');
create table rify_user
(
    id          serial primary key not null,
    account     varchar(20) unique not null,
    password    varchar(100)       not null,
    email       varchar(50)        not null,
    status      rify_user_status,
    role        varchar(100),
    permissions varchar array,
    create_by   varchar(20),
    create_time timestamp with time zone,
    update_by   varchar(20),
    update_time timestamp with time zone
);

comment on table rify_user is '用户表';
comment on column rify_user.id is '用户 ID';
comment on column rify_user.account is '账号';
comment on column rify_user.password is '密码';
comment on column rify_user.email is '邮箱';
comment on column rify_user.status is '状态: 是否启用, 是否删除, 是否锁定';
comment on column rify_user.permissions is '权限列表';
comment on column rify_user.create_by is '创建人';
comment on column rify_user.create_time is '创建时间';
comment on column rify_user.update_by is '更新人';
comment on column rify_user.update_time is '更新时间';

alter sequence rify_user_id_seq restart with 1000000001 increment by 1;

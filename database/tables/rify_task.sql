drop table if exists rify_task;
drop type if exists rify_task_misfire;
drop type if exists rify_task_status;

create type rify_task_misfire as enum ('DEFAULT');
create type rify_task_status as enum ('NORMAL', 'PAUSE');
create table rify_task
(
    id              serial primary key not null,
    name            varchar            not null,
    target          varchar            not null,
    group_id        varchar,
    cron_expression varchar            not null,
    misfire_policy  rify_task_misfire default 'DEFAULT',
    status          rify_task_status  default 'NORMAL',
    create_by       varchar(20),
    create_time     timestamp with time zone,
    update_by       varchar(20),
    update_time     timestamp with time zone,
    remark          text
);

comment on table rify_task is '系统任务表';
comment on column rify_task.id is '任务 id';
comment on column rify_task.name is '任务名称';
comment on column rify_task.group_id is '任务分组 id';
comment on column rify_task.cron_expression is 'cron 执行表达式';
comment on column rify_task.misfire_policy is '补偿机制';
comment on column rify_task.status is '任务状态';
comment on column rify_task.create_by is '创建人';
comment on column rify_task.create_time is '创建时间';
comment on column rify_task.update_by is '更新人';
comment on column rify_task.update_time is '更新时间';

alter sequence rify_task_id_seq restart with 10000001 increment by 1;

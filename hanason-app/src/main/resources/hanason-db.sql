create database `hanason-app`;
use `hanason-app`;

create table `sys_user`
(
    id              bigint(20)  not null auto_increment comment 'id',
    phone           char(11)    not null comment '手机号',
    device_id       varchar(40) not null comment '设备id-也即是alias',
    tag             varchar(20) not null comment '应用标识',
    platform        varchar(20) not null comment '发布应用的平台 baidu、mi等',
    version         varchar(20) not null comment '当前应用的版本',
    ip              bigint(20)  not null comment '用户登录的ip',
    last_ip         bigint(20)  null comment '上次登录的ip',
    enable          tinyint(1)  not null comment '是否启用',
    status          tinyint(1)  not null comment '用户当前的状态',
    login_time      timestamp   not null default current_timestamp comment '当前登录的时间',
    last_login_time timestamp   null comment '上次登录的时间',
    user_type       varchar(15) null comment '用户类别,使用枚举，存枚举的值',
    primary key (id)
) engine innodb
  default charset utf8;

alter table `sys_user` add unique index `idx_phone` (phone);


create table `sys_user_register`
(
    id                bigint(20)  not null auto_increment comment 'id',
    phone             char(11)    not null comment '手机号',
    register_tag      varchar(20) not null comment '应用标识',
    register_platform varchar(20) not null comment '发布应用的平台 baidu、mi等',
    register_version  varchar(20) not null comment '当前应用的版本',
    register_system   varchar(10) not null comment '系统',
    created           timestamp   not null default current_timestamp() comment '创建时间'

) engine innodb
  default charset utf8;

alter table `sys_user_register` add unique index `idx_phone` (phone);

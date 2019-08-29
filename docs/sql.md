```mysql
create database base_platform;

use base_platform;

create table if not exists event (
  id int(5) not null auto_increment primary key ,
  platform enum('consumer', 'merchant', 'manager') not null,
  event_key varchar(20) not null ,
  description varchar(100) not null,
  create_time timestamp not null default current_timestamp comment '创建时间',
  update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间'
) default charset = utf8;

insert event values (null, 'consumer', 'main', '描述信息随意啦', now(), now());
```
create database base_platform;

use base_platform;

create table if not exists event (
  id int(5) not null auto_increment primary key ,
  platform enum('consumer', 'merchant', 'manager') not null,
  event_key varchar(20) not null ,
  description varchar(100) not null
) default charset = utf8;
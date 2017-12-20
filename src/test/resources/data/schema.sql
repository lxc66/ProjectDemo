drop table tb_dept if exists;
drop table tb_user if exists;
create table tb_dept (id char(32) not null, name varchar(255), primary key (id));
create table tb_user (id char(32) not null, id_dept varchar(32), name varchar(255), age integer, primary key (id));

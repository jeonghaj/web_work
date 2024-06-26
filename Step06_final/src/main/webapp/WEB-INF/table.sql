create table user_info(id varchar2(100) constraint user_info_id_pk primary key,
pwd varchar2(100) constraint user_info_pwd_nn not null,
email varchar2(100),
profile varchar2(100),
regdate date
); 

create table board_file(
num number primary key,
writer varchar2(100) not null,
title varchar2(100) not null,
orgFileName varchar2(100) not null,
saveFileName varchar2(100) not null,
fileSize number not null,
regdate date
);

create sequence board_file_seq;
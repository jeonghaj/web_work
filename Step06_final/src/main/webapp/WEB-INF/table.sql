create table user_info(id varchar2(100) constraint user_info_id_pk primary key,
pwd varchar2(100) constraint user_info_pwd_nn not null,
email varchar2(100),
profile varchar2(100),
regdate date
); 
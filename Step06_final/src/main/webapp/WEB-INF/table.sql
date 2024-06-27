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

create table board_cafe(
num number primary key, --글번호
writer varchar2(100) not null, --작성자
title varchar2(100) not null, --제목
content CLOB, --글 내용
viewCount number, --조회수
regdate date --글 작성일
);
--게시글 번호를 얻어낼 시퀀스
create sequence board_cafe_seq;

--어떤 세션에서 몇번글을 읽었는지 정보를 저장할 테이블
create table readed_data(
num number references board_cafe(num),
session_id varchar2(50)
);
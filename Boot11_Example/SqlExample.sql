 CREATE TABLE user_tbl
  (id NUMBER PRIMARY KEY,
  	userName VARCHAR2(20) UNIQUE,
  	password VARCHAR2(100) NOT NULL,
   	email VARCHAR2(100),
   	role VARCHAR2(20) NOT NULL,
   	profile VARCHAR2(100),
   	regdate DATE
  );
  
  SQL> create sequence user_seq;
  
  
  
  -- 이미지 갤러리를 만들기 위한 테이블
  create table board_gallery(
  num number primary key,
  writer varchar2(100),
  caption varchar2(100), --이미지에 대한 설명
  saveFileName varchar2(100), --업로드 된 이미지의 저장된 이름
  regdate date -- 이미지 업로드 날짜
  );
  
  select * from board_gallery order by num desc;
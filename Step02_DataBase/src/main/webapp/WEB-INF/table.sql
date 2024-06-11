-- 방명록 글을 저장할 테이블
CREATE TABLE board_guest(
	num NUMBER PRIMARY KEY, -- 글번호 
	writer VARCHAR2(30),	-- 작성자 
	content VARCHAR2(100) NOT NULL, -- 글내용
	pwd VARCHAR2(30) NOT NULL, -- 비밀번호 
	regdate DATE	-- 글 등록일 
);

-- 방명록 글번호를 얻어낼 시퀀스
CREATE SEQUENCE board_guest_seq;
	
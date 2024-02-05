-- 가게 정보 테이블
CREATE TABLE tbl_place(
    place_seq number(8),
    name varchar2(100) NOT NULL,
    phone varchar2(100),
    rate NUMBER(3,2),
    open_time varchar2(50) NOT NULL,
    close_time varchar2(50) NOT NULL,
    food_type  varchar2(50)
);


-- 시퀀스 생성
CREATE SEQUENCE place_seq;

-- 기본 주소록 테이블
CREATE TABLE tbl_place_address (
	address_seq number(8) ,  
	address varchar2(500) NOT NULL
);


-- 시퀀스 생성
CREATE SEQUENCE address_seq;


-- 메뉴 정보 테이블
CREATE TABLE tbl_menu (
	menu_seq number(8)  ,
	place_seq number(8) NOT NULL,
	menu_name varchar2(20) NOT NULL ,	
	price number(8) NOT NULL			
);


-- 시퀀스 생성
CREATE SEQUENCE menu_seq;


-- 지역 테이블 
CREATE TABLE tbl_area_unit (
	area_unit_code number(8) PRIMARY KEY,  
	unit_name varchar2(50) NOT NULL
);





--1. 맛집찾기
--(이름,평점,메뉴)

SELECT *
FROM TBL_PLACE tp , TBL_MENU tm 
WHERE tp.PLACE_SEQ  = tm.PLACE_SEQ 
AND tp.NAME LIKE '%' || '낙곱새' || '%'
AND RATE >= 4.5
AND MENU_NAME LIKE '%' || '낙곱새' || '%';

--3. 지역별로 찾기 
SELECT   tp.place_seq
      ,tp.name
      , open_time 
        , close_time
        , tpa.address
FROM  tbl_place tp
   , tbl_place_address tpa
   , tbl_area_unit au
WHERE tp.place_seq = tpa.place_seq
  AND substr(tpa.address,0,2) = au.unit_name
  --  서울 / 인천 /부산 /대구 /광주 / 제주 검색
  AND au.unit_name = '대구'
;  

-- 4. 평점순(5점 만점) 으로 보여주기
SELECT tp.PLACE_SEQ, tp.NAME, open_time , close_time
        , tpa.address, tp.rate
FROM  tbl_place tp
   , tbl_place_address tpa
WHERE tp.place_seq = tpa.place_seq
-- 평점 높은 순으로 정렬
ORDER BY rate DESC;


-- 5. 수정(평점) 
UPDATE tbl_place 
SET rate = '4.7'
WHERE place_seq = 22 ;

--랜덤
SELECT * 
FROM
	(SELECT * 
	FROM TBL_PLACE tp, 
	TBL_PLACE_ADDRESS tpa 
	WHERE ADDRESS like '%'||?||'%'
	ORDER BY DBMS_RANDOM.VALUE) 
WHERE ROWNUM <= ?";

--
SELECT *
FROM (
   SELECT   tp.place_seq
         ,tp.name
         , open_time 
           , close_time
           , tpa.address
   FROM  tbl_place tp
      , tbl_place_address tpa
      , tbl_area_unit au
   WHERE tp.place_seq = tpa.place_seq
     AND substr(tpa.address,0,2) = au.unit_name
     AND au.unit_name = '대구'
   ORDER BY DBMS_RANDOM.VALUE
)
WHERE ROWNUM <=3;




























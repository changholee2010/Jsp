select * from tab;

-- table for template.
-- 상품번호, 상품코드, 상품명, 상품설명, 상품상세설명, 대표이미지1, 대표이미지2, 대표이미지3, 생성일자.
create sequence prod_seq; -- 시퀀스.
drop table template_prod purge;
create table template_prod (
 prod_no number,
 prod_code varchar2(30),
 prod_name varchar2(200),
 prod_desc varchar2(300),
 prod_content varchar2(4000),
 prod_img1 varchar2(100),
 prod_img2 varchar2(100),
 prod_img3 varchar2(100),
 attribute1 varchar2(100), --속성1 미정의 상태로 추후 사용할 칼럼.
 attribute2 varchar2(100), --속성2 미정의 상태로 추후 사용할 칼럼.
 attribute3 varchar2(100), --속성3 미정의 상태로 추후 사용할 칼럼.
 attribute4 varchar2(100), --속성4 미정의 상태로 추후 사용할 칼럼.
 attribute5 varchar2(100), --속성5 미정의 상태로 추후 사용할 칼럼.
 creation_date date default sysdate,
 trial_no varchar2(20) -- 2024-06-29-13-12(년-월-일-시-분)
);

-- create table.
drop table prod_tbl purge;
create table prod_tbl (
 prod_no number primary key,
 prod_name varchar2(300) not null,
 prod_desc varchar2(500) not null,
 prod_content varchar2(4000),
 creation_date date default sysdate,
 --code char(1) CHECK(code in ('A','B')),
 ord_no number not null
);
 
create sequence prod_seq;

select *
from prod_tbl
order by ord_no;

delete from prod_tbl;

create table tbl_student (
 std_no varchar2(20) primary key,
 std_name varchar2(200) not null,
 phone varchar2(20),
 bld_type varchar2(10),
 create_date date default sysdate
);

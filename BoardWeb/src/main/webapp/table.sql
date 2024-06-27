select * from tab;

-- create table.
drop table prod_tbl purge;
create table prod_tbl (
 prod_no number primary key,
 prod_name varchar2(300) not null,
 prod_desc varchar2(500) not null,
 prod_content varchar2(4000),
 creation_date date default sysdate,
 --code char(1) CHECK(code in ('A','B')),
 ord_no number not null);
 
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
 create_date date default sysdate);


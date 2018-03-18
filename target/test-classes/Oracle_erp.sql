drop sequence erp_user_id;
drop sequence erp_emp_id;

create sequence erp_user_id start with 2;
create sequence erp_emp_id start with 11;

drop table erp_user;
drop table erp_emp;

create table erp_user(
	id			number(10),
	username	varchar2(25),
	password	varchar2(25)
);

alter table erp_user
add constraint erp_user_id_pk primary key(id);

alter table erp_user
add constraint erp_user_un_nn check(username is not null);

alter table erp_user
add constraint erp_user_un_uq unique(username);

insert into erp_user values(1,'yuri','yuri');

commit;


create table erp_emp(
	id			number(10),
	headImage	varchar2(255),
	name		varchar2(25),
	hiredate	Date,
	gender		varchar2(25),
	salary		number(10,2)
);

alter table erp_emp
add constraint erp_emp_id_pk primary key(id);

alter table erp_emp
add constraint erp_emp_name_nn check(name is not null);

alter table erp_emp
add constraint erp_emp_name_uq unique(name);

insert into erp_emp values(1,'default.jpg','Tina',sysdate,'FEMALE',8888);
insert into erp_emp values(2,'default.jpg','Micheal',sysdate,'MALE',666);
insert into erp_emp values(3,'default.jpg','Tim',sysdate,'MALE',5555);
insert into erp_emp values(4,'default.jpg','Jeff',sysdate,'MALE',7733);
insert into erp_emp values(5,'default.jpg','Bob',sysdate,'MALE',22222);
insert into erp_emp values(6,'default.jpg','Grace',sysdate,'FEMALE',3366);
insert into erp_emp values(7,'default.jpg','Jenny',sysdate,'FEMALE',58881);
insert into erp_emp values(8,'default.jpg','Rob',sysdate,'MALE',98337);
insert into erp_emp values(9,'default.jpg','Araon',sysdate,'MALE',99999);
insert into erp_emp values(10,'default.jpg','Robert',sysdate,'MALE',16691);

commit;

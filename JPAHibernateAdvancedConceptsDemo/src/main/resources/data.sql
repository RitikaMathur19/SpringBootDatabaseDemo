insert into course(id,name,created_date,last_updated_date,is_deleted) values (1001,'Hindi',sysdate(),sysdate(),false);
insert into course(id,name,created_date,last_updated_date,is_deleted) values (1002,'English',sysdate(),sysdate(),false);
insert into course(id,name,created_date,last_updated_date,is_deleted) values (1003,'Maths',sysdate(),sysdate(),false);
insert into course(id,name,created_date,last_updated_date,is_deleted) values (1004,'Science',sysdate(),sysdate(),false);
insert into course(id,name,created_date,last_updated_date,is_deleted) values (1005,'History',sysdate(),sysdate(),false);
insert into course(id,name,created_date,last_updated_date,is_deleted) values (1006,'Social Science',sysdate(),sysdate(),false);
/*insert into course(id,name,created_date,last_updated_date) values (1007,'Dummy2',sysdate(),sysdate());
insert into course(id,name,created_date,last_updated_date) values (1008,'Dummy3',sysdate(),sysdate());
*/
insert into course(id,name,created_date,last_updated_date,is_deleted) values (1009,'Moral Science',sysdate(),sysdate(),false);
/*insert into course(id,name,created_date,last_updated_date) values (1010,'Dummy5',sysdate(),sysdate());
insert into course(id,name,created_date,last_updated_date) values (1011,'Dummy6',sysdate(),sysdate());
insert into course(id,name,created_date,last_updated_date) values (1012,'Dummy7',sysdate(),sysdate());
insert into course(id,name,created_date,last_updated_date) values (1013,'Dummy8',sysdate(),sysdate());
insert into course(id,name,created_date,last_updated_date) values (1014,'Dummy9',sysdate(),sysdate());
insert into course(id,name,created_date,last_updated_date) values (1015,'Dummy10',sysdate(),sysdate());
*/
insert into passport(id,number) values (4001,'E1234');
insert into passport(id,number) values (4002,'F5454');
insert into passport(id,number) values (4003,'I6565');
insert into passport(id,number) values (4004,'N76767');
insert into passport(id,number) values (4005,'RA5656');

insert into student(id,name,passport_id) values (2001,'Ritu',4001);
insert into student(id,name,passport_id) values (2002,'Deepu',4002);
insert into student(id,name,passport_id) values (2003,'Isha',4003);
insert into student(id,name,passport_id) values (2004,'Neha',4004);
insert into student(id,name,passport_id) values (2005,'Ayesha',4005);

insert into review(id,rating,description,course_id,student_id) values (5001,'5','Awesome Learning','1001','2001');
insert into review(id,rating,description,course_id,student_id) values (5002,'5','Great Learning','1001','2002');
insert into review(id,rating,description,course_id,student_id) values (5003,'4','Enjoyed Learning','1003','2002');
insert into review(id,rating,description,course_id,student_id) values (5004,'4','Wonderful Learning','1005','2004');

insert into student_course(student_id,course_id) values (2001,1001);
insert into student_course(student_id,course_id) values (2002,1001);
insert into student_course(student_id,course_id) values (2001,1004);
insert into student_course(student_id,course_id) values (2003,1004);
insert into student_course(student_id,course_id) values (2004,1001);
insert into student_course(student_id,course_id) values (2002,1005);

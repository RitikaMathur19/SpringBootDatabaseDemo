/*create table PERSON
(
   id integer not null,
   name varchar(255) not null,
   location varchar(255),
   birth_date timestamp,
   primary key(id)
);
*/

INSERT INTO person (ID,name,location,birth_date)
VALUES (10001,'Ritu','Pune',sysdate());
INSERT INTO person (ID,name,location,birth_date)
VALUES (10002,'Kaju','Noida',sysdate());
INSERT INTO person (ID,name,location,birth_date)
VALUES (10003,'Kishmish','Pune',sysdate());
INSERT INTO person (ID,name,location,birth_date)
VALUES (10004,'Badam','Bangalore',sysdate());
INSERT INTO person (ID,name,location,birth_date)
VALUES (10005,'Badam','Hyderabad',sysdate());
INSERT INTO person (ID,name,location,birth_date)
VALUES (10006,'Badam','Pune',sysdate());

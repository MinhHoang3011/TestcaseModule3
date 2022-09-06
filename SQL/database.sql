create database `testcase`;
use testcase;



create table student(
`ID` int auto_increment primary key ,
`NAME` nvarchar(100) not null ,
`DateOfBirth` date not null ,
`ADDRESS` nvarchar(100) not null ,
`PHONE` varchar(20) not null unique ,
`EMAIL` varchar(100) not null ,
`ClassID` int not null,
foreign key (ClassID) references ClassRoom(ID)
);

select * from student;

insert into student (NAME, DateOfBirth, ADDRESS, PHONE, EMAIL, ClassID) value ('Phạm Minh Hoàng','1998-11-30','Hà Nam','0964002838','minhhoang301198@gmail.com',1);
insert into student (NAME, DateOfBirth, ADDRESS, PHONE, EMAIL, ClassID) value ('Ngô Trọng Hiếu','1998-04-16','Lào Cai','0979276402','imbakito1604@gmail.com',2);
insert into student (NAME, DateOfBirth, ADDRESS, PHONE, EMAIL, ClassID) value ('Trần Xuân Ba','1995-07-21','Thái Bình','0355210795','batran2107@gmail.com',3);

select * from student;

create table `ClassRoom` (
`ID` int not null auto_increment primary key ,
`NAME` varchar(100) not null
);

insert into ClassRoom (NAME) value ('C0522G1');
insert into ClassRoom (NAME) value ('C0622G1');
insert into ClassRoom (NAME) value ('C0722G1');
insert into ClassRoom (NAME) value ('C0822G1');
insert into ClassRoom (NAME) value ('C0922G1');

select * from ClassRoom;
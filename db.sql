create table USERS(
	ACCOUNT_NO serial primary key,
	NAME varchar(30) not null,
	GENDER varchar(6) not null,
	DOB varchar(10) not null,
	MOBILE_NO varchar(10) unique not null,
	EMAIL varchar(30) unique not null,
	PASSWORD varchar(15) not null,
	ADDRESS varchar(100) not null,
	BALANCE decimal(12,2) default 500 not null
);

insert into USERS(NAME,GENDER,DOB,MOBILE_NO,EMAIL,PASSWORD,ADDRESS) values('pavis','male','30-05-1997','1234567890','a@b.com','123','Anna University');

create table BENIFITARY_ACCOUNTS(
	ID serial primary key,
	USER_ID serial references USERS(ACCOUNT_NO),
	NAME varchar(30),
	ACCOUNT_NO serial references USERS(ACCOUNT_NO),
	unique (USER_ID,ACCOUNT_NO)
);

create table Transactions(
	ID serial primary key,
	FROM_ACCOUNT serial references USERS(ACCOUNT_NO),
	TO_ACCOUNT serial references USERS(ACCOUNT_NO),
	amount decimal(12,2),
	timestamp timestamp default current_timestamp
);
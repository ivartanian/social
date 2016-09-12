DROP TABLE IF EXISTS Account;
create table Account (id bigint,
						username varchar unique,
						password varchar not null,
						firstName varchar not null, 
						lastName varchar not null,
						primary key (id));

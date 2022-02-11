drop table if exists `fizzy_drink` CASCADE;
create table fizzy_drink (
id bigint AUTO_INCREMENT,
brand varchar(255),
name varchar(255),
sugar_content integer not null,
flavour varchar(255),
primary key (id)
);
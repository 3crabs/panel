create table permissions
(
    code    varchar(100) not null primary key,
    name    varchar(100) not null unique,
    created timestamp    not null
);

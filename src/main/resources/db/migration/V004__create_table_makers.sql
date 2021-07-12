create table makers
(
    code           varchar(100) not null primary key,
    role_code_who  varchar(100) references roles on delete cascade,
    role_code_whom varchar(100) references roles on delete cascade,
    created        timestamp    not null
);

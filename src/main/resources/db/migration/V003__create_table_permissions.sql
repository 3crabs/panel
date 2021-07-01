create table permissions
(
    code          varchar(100) not null primary key,
    role_code     varchar(100) references roles on delete cascade,
    function_code varchar(100) references functions on delete cascade,
    created       timestamp    not null
);


create table if not exists skunks (
    id VARCHAR(36) primary key not null,
    name varchar(50) not null,
    size double(6,2) not null,
    weight double(6,2) not null,
    color varchar(50)  not null,
    is_omnivorous tinyint(1) default true,
    family_name varchar(50) not null,
    created_on timestamp default CURRENT_TIMESTAMP,
    updated_on timestamp ,
    isDeleted tinyint(1) default false,
    deleted_on timestamp
);


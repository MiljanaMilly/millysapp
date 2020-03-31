
create table if not exists skunks (
    id bigint(200) primary key auto_increment not null,
    name varchar(50) unique not null,
    size double(6,2) not null,
    weight double(6,2) not null,
    color varchar(50)  not null,
    is_omnivorous tinyint(1) default true,
    family_name varchar(50) not null
);



create table if not exists skunks (
    id bigint(200) primary key auto_increment not null,
    name varchar(50) not null,
    size double(3,2) not null,
    weight double(3,2) not null,
    color varchar(50)  not null,
    is_omnivorous tinyint(1) default true,
    family_name varchar(50) not null
);

# insert into skunks(name, size, weight, color, is_omnivorous, family_name)
# values ('spotted skunk', 25 , 8 , 'black', 1, 'jkjkggskh');
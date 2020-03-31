
create table if not exists skunks (
    id bigserial primary key,
    name varchar(255) unique not null,
    size double precision not null,
    weight double precision,
    color varchar(255),
    is_omnivorous boolean default true,
    family_name varchar(255)
);


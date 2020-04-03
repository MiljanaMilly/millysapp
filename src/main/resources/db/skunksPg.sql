
create table if not exists skunks (
    id varchar(255) primary key,
    name varchar(255) unique not null,
    size double precision not null,
    weight double precision,
    color varchar(255),
    is_omnivorous boolean default 'True',
    family_name varchar(255),
    created_on timestamp with time zone default CURRENT_TIMESTAMP,
    updated_on timestamp with time zone,
    isDeleted boolean default 'False',
    deleted_on timestamp with time zone
);


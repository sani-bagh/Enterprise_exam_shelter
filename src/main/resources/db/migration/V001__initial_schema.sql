create table animals (
                         animal_id serial primary key,
                         animal_type varchar(50) not null,
                         animal_name varchar(50) not null,
                         vaccinated boolean not null,
                         date_of_birth date not null,
                         registered timestamp    not null,
                         cage_nr integer not null

);

create table users
(
    user_id       serial primary key,
    user_created  timestamp    not null,
    user_email    varchar(255) not null,
    user_enabled  boolean      not null,
    user_password varchar(255) not null
);

create table authorities
(
    authority_id   serial primary key,
    authority_name varchar(255)
);

create table users_authorities
(
    user_entity_user_id      bigint,
    authorities_authority_id bigint
);
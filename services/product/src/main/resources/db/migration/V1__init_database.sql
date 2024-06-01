create table if not exists category(
    id integer not null primary key,
    description varchar(255),
    name varchar(255)
);

create table if not exists product (
                                       id integer not null primary key,
                                       category_id integer,
                                       description varchar(255),
                                       name varchar(255),
                                       available_quantity double precision not null,
                                       price numeric(38, 2),
                                       constraint fk1mjngsajnojnwb foreign key (category_id) references category(id)
);


create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;
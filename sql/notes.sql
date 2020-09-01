create table api_log
(
    create_time timestamp not null,
    ip varchar(40) not null,
    user_id int null,
    method varchar(10) null,
    uri varchar(255) null,
    args mediumtext null,
    code int null,
    msg varchar(255) null,
    data mediumtext null,
    primary key (create_time, ip)
);

create table image
(
    note_id int not null,
    no int not null,
    data mediumblob null,
    primary key (note_id, no)
);

create table invite_code
(
    code varchar(255) not null
        primary key
);

create table note
(
    id int not null
        primary key,
    user_id int not null,
    type enum('document', 'folder') not null,
    name varchar(255) null,
    parent_id int null,
    order_no int default 0 not null,
    content mediumtext null
);

create table "user"
(
    id int not null
        primary key,
    username varchar(16) not null,
    password varchar(255) null,
    constraint uni_username
        unique (username)
);

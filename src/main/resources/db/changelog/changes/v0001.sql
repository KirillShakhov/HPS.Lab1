create table categories
(
    id         bigint       not null
        primary key,
    name       varchar(255) not null,
    short_name varchar(255)
);

alter table categories
    owner to postgres;

create table payments
(
    id          bigint       not null
        primary key,
    create_date varchar(255) not null,
    description varchar(255),
    token       varchar(255) not null
);

alter table payments
    owner to postgres;

create table photos
(
    id          bigint       not null
        primary key,
    base64      varchar(255) not null,
    create_date varchar(255) not null,
    type        integer      not null
);

alter table photos
    owner to postgres;

create table roles
(
    id   bigint not null
        primary key,
    name varchar(255)
);

alter table roles
    owner to postgres;

create table users
(
    id         bigint       not null
        primary key,
    active     boolean      not null,
    email      varchar(255) not null
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    first_name varchar(255) not null,
    last_name  varchar(255),
    pass       varchar(255) not null,
    username   varchar(255) not null
        constraint uk_r43af9ap4edm43mmtq01oddj6
            unique
);

alter table users
    owner to postgres;

create table chats
(
    id       bigint not null
        primary key,
    admin_id bigint not null
        constraint fkgnpfufpa3g6wpjcp5wqdvjlsw
            references users
);

alter table chats
    owner to postgres;

create table messages
(
    id          bigint       not null
        primary key,
    create_date varchar(255) not null,
    message     varchar(255) not null,
    user_id     bigint       not null
        constraint fkpsmh6clh3csorw43eaodlqvkn
            references users
);

alter table messages
    owner to postgres;

create table orders
(
    id            bigint not null
        primary key,
    delivery_info varchar(255),
    payment_id    bigint not null
        constraint fk8aol9f99s97mtyhij0tvfj41f
            references payments,
    user_id       bigint not null
        constraint fk32ql8ubntj5uh44ph9659tiih
            references users
);

alter table orders
    owner to postgres;

create table products
(
    id          bigint not null
        primary key,
    description varchar(255),
    category_id bigint not null
        constraint fkog2rp4qthbtt2lfyhfo32lsw9
            references categories,
    user_id     bigint not null
        constraint fkdb050tk37qryv15hd932626th
            references users,
    name        text
);

alter table products
    owner to postgres;

create table disputes
(
    id         bigint  not null
        primary key,
    is_clothed boolean not null,
    order_id   bigint  not null
        constraint fk7w9qai75udrw8yjppow8vqxa
            references orders,
    product_id bigint  not null
        constraint fkrq4bt83knyhqycoyt1ahyfgub
            references products,
    user_id    bigint  not null
        constraint fkfvupul0vcl8u4ctf5f1ggc39v
            references users
);

alter table disputes
    owner to postgres;

create table categories_tags
(
    category_id bigint not null
        constraint fkta6sh77i4jp6mh8t2hm4rom39
            references categories,
    tag         varchar(255)
);

alter table categories_tags
    owner to postgres;

create table chat_messages
(
    chat_id    bigint not null
        constraint fkt56nsqjwt7t4sian6vts9wg3t
            references chats,
    message_id bigint not null
        constraint fkidtmi2nl0lm9bevmrtrts9k5p
            references messages,
    primary key (chat_id, message_id)
);

alter table chat_messages
    owner to postgres;

create table chat_users
(
    chat_id bigint not null
        constraint fklblrwneuhods5qvh9od8yyewy
            references chats,
    user_id bigint not null
        constraint fkbbuxrxc67blnxsexqdlfmrpbh
            references users,
    primary key (chat_id, user_id)
);

alter table chat_users
    owner to postgres;

create table message_attachments
(
    message_id    bigint not null
        constraint fkj7twd218e2gqw9cmlhwvo1rth
            references messages,
    attachment_id bigint not null
        constraint fkqqyjeooqvl4wcq4aed6aksmuy
            references photos,
    primary key (message_id, attachment_id)
);

alter table message_attachments
    owner to postgres;

create table order_products
(
    order_id   bigint not null
        constraint fkawxpt1ns1sr7al76nvjkv21of
            references orders,
    count      integer,
    product_id bigint not null
        constraint fkdxjduvg7991r4qja26fsckxv8
            references products,
    primary key (order_id, product_id)
);

alter table order_products
    owner to postgres;

create table product_attachments
(
    product_id    bigint not null
        constraint fkm0etxe60of1sbmn82p38nwnsq
            references products,
    attachment_id bigint not null
        constraint fk5k9p10au76fy2rfomwd1tkoei
            references photos,
    primary key (product_id, attachment_id)
);

alter table product_attachments
    owner to postgres;

create table product_comments
(
    product_id bigint not null
        constraint fklvw9kwav1pell1wg6xo0dmme6
            references products,
    comment_id bigint not null
        constraint fkdxbisu1ucgpealxuild0g67yn
            references messages,
    primary key (product_id, comment_id)
);

alter table product_comments
    owner to postgres;

create table users_and_roles
(
    user_id bigint not null
        constraint fklfn8h5l2ficgfsc6v5xl6txag
            references users,
    role_id bigint not null
        constraint fkogbat8yxj8xb3jdpe5mm737lx
            references roles,
    primary key (user_id, role_id)
);

alter table users_and_roles
    owner to postgres;


begin transaction;

create table users
(
  id       bigserial primary key,
  username varchar(50)  not null check ( username <> '' ) unique,
  password varchar(100) not null,
  name     varchar(50),
  surname  varchar(50),
  email    varchar(50),
  -- 4096 - maximum path length
  image    varchar(4096)
);

create table lists
(
  id   bigserial primary key,
  name varchar(50) not null,
  icon varchar(4096)
);

create table user_lists
(
  userID bigint references users (id),
  listID bigint references lists (id),
  primary key (userID, listID)
);

create table tasks
(
  id          bigserial primary key,
  list_id     bigint references lists (id),
  title       varchar(50) not null,
  important   boolean,
  date        date,
  description varchar(255)
);

create table comments
(
  id     bigserial primary key,
  taskID bigint references tasks (id),
  userID bigint references users (id),
  text   text not null,
  date   date,
  time   time
);

end transaction;
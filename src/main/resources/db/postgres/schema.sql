begin transaction;

create table users
(
  id       bigserial primary key,
  username varchar(50) not null check ( username <> '' ) unique,
  password varchar(100) not null,
  name     varchar(50),
  surname  varchar(50),
  email    varchar(50),
  image    bytea
);

create table lists
(
  id   bigserial primary key,
  name varchar(50) not null,
  icon bytea
);

create table user_lists
(
  userID bigint references users (id) on delete cascade,
  listID bigint references lists (id) on delete cascade,
  primary key (userID, listID)
);

create table tasks
(
  id          bigserial primary key,
  listId      int references lists (id) on delete cascade,
  title       varchar(50) not null,
  important   boolean,
  date        date,
  description varchar(255)
);

create table comments
(
  id     bigserial primary key,
  taskID int references tasks (id) on delete cascade,
  userID int references users (id) on delete cascade,
  text   text not null,
  date   date,
  time   time
);

end transaction;
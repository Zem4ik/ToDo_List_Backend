create table users
(
  id       identity primary key,
  username varchar(50) not null unique,
  password varchar(100) not null,
  name     varchar(50),
  surname  varchar(50),
  email    varchar(50),
-- 4096 - maximum path length
  image    varchar(4096)
);

create table lists
(
  id   identity primary key,
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
  id          identity primary key,
  list_id     bigint references lists (id),
  title       varchar(50) not null,
  important   boolean,
  date        date,
  description varchar(255)
);

create table comments
(
  id     identity primary key,
  taskID int references tasks (id),
  userID int references users (id),
  text   text not null,
  date   date,
  time   time
);
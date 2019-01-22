-- denormalization down to 2NF
alter table lists add column userID bigint references users (id);
drop table user_lists;

-- denormalization down to 1NF
alter table tasks drop constraint tasks_listid_fkey;
alter table lists drop constraint lists_pkey;
alter table lists add constraint lists_pkey primary key (id, userID);
alter table lists add constraint id_unique unique(id);
alter table tasks add constraint tasks_listid_fkey foreign key(listid) references lists(id);

-- denormalization down to 0NF
alter table lists drop column userID cascade;
alter table lists add column userIDs text;
insert into users(username, password) VALUES ('user1', 'user1');
insert into users(username, password) VALUES ('user2', 'user2');
insert into lists(name, icon, userids) values ('testList', null, '1, 2')
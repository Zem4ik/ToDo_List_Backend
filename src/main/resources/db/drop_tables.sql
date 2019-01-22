begin transaction;

drop table if exists users cascade;
drop table if exists lists cascade;
drop table if exists user_lists cascade;
drop table if exists tasks cascade;
drop table if exists comments cascade;

end transaction;
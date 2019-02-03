create table if not exists users(
	id uuid primary key,
	name varchar,
	age int,
	date date,
	gender varchar,
	email varchar
)
create table statistic (
	int1 integer not null,
	int2 integer not null,
	limit integer not null,
	str1 varchar(255) not null,
	str2 varchar(255) not null,
	count integer not null,
	primary key (int1, int2, limit, str1, str2)
)
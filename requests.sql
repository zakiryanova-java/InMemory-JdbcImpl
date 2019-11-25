CREATE table houses
(
    id          integer primary key autoincrement ,
    price       integer check ( price > 0 ),
    district    text,
    underground text
);

insert into houses values (1,200000,'Авиастроительный','Авиастроительный');
insert into houses values (2,3500000, 'Советский', 'Аметьево');
insert into houses values (3,1500000, 'Московский', 'Яшьлек');


select price, district from houses;

insert into houses values (4,750000, 'Приволжский','Проспект Победы');
insert into houses values (5,8000000,'Вахитовский','Кольцо');
insert into houses values (6,8000000,'Приволжский','Проспект Победы');

DROP TABLE houses;
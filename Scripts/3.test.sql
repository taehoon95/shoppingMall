select user(),database();

desc customer;
desc product;
desc sale;

select * from customer;
select * from product;
select * from sale;

-- Main 화면 형식 sql문
create view vw_all
as
select date_format(date, "%Y.%m.%d") as date ,
	   c.cusNo,
	   c.passno,
	   c.cusName,
	   callNo,
	   p.procode,
	   p.proname,
	   p.proprice,
	   saleamount,
	   proPrice*saleamount*1.1 as sales,
	   p.proprice*saleamount*1.1 - p.proPrice*saleamount as profit 
  from sale s join product p on s.procode = p.procode join customer c on s.cusNo = c.cusNo
  order by date, c.cusNo;

drop view vw_all;

select date,cusNo,cusName,callNo,procode,saleamount,sales from vw_all;

-- Main 화면에서 날짜별 조회
select * from vw_main where date = '2012.03.24';

-- 제품별 조회

select cusNo,`date`,procode,proName,saleamount,proprice,sales,profit from vw_all;

-- 상세 조회 
 
select date,procode,proName,cusName,saleamount,proPrice,sales,profit,cusno from vw_all;

-- sale테이블에서 insert,update,delete
insert into sale (date,cusno ,procode ,saleamount) values ('20210314','12004','PB',10);

update sale set date = '20210314' ,procode = 'PC',saleamount = 9 where cusno = '12004';

delete from sale where date = '20210314' and cusno = '12004' and procode = 'PC';


truncate table sale;

select * from sale;


-- Product 테이블에서 insert,update,delete
insert into product values('PI','분무기',600,20);

select * from product;

update product set proname = '물총', proprice = 1000, stock = 100 where procode = 'PI';

delete from product where procode = 'PI';

-- Customer 테이블에서 insert,update,delete
desc customer ;

insert into customer values('12011',111,'이태훟','19950304','01045105881',1);

update customer set cusname ='이태훈', callno = '010-1545-6351' where cusno = '12011';

delete from customer where cusno = '12011';

select * from customer;








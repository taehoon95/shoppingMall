select user(),database();

use shopping;

desc customer;
desc product;
desc sale;

insert into customer (cusno, passno, cusname, birth, callno, sex)
     values ('12001',password(123),'홍길동','1977.04.05','010-9741-5821',1),
     	    ('12002',password(123),'김연수','1985.03.01','010-4568-5581',2),
     	    ('12003',password(123),'김지원','1986.07.08','010-9111-5556',2),
     	    ('12004',password(123),'문희원','1980.01.08','010-7777-7777',2),
     	    ('12005',password(123),'유일한','1979.02.05','010-4566-8886',1),
     	    ('12006',password(123),'김동수','1981.11.23','010-1231-1211',1),
     	    ('12007',password(123),'배진태','1982.07.07','010-7877-7777',1),
     	    ('12008',password(123),'류은수','1983.03.01','010-7444-1474',2),
     	    ('12009',password(123),'김동철','1987.04.26','010-8525-1235',1),
     	    ('12010',password(123),'최홍석','1990.04.05','010-3214-6547',1);


     	
select * from customer;

insert into product (procode, proname, proprice, stock)
     values ('PA','책상',10000,100),
     	    ('PB','냉장고',36000,40),
     	    ('PC','세탁기',22000,80),
     	    ('PD','VTR',30000,100),
     	    ('PE','자전거',90000,60),
     	    ('PF','시계',6000,10),
     	    ('PG','TV',8000,50),
     	    ('PH','탁자',3000,100);
     	    
select * from product;

insert into sale (date, cusno, procode, saleamount)
	 values ('2021.01.24','12003','PA',2),
	        ('2021.01.27','12001','PA',1),
	        ('2021.02.03','12009','PC',1),
	        ('2021.02.07','12010','PF',5),
	        ('2021.02.13','12003','PF',4),
	        ('2021.02.14','12002','PE',3),
	        ('2021.02.14','12004','PH',10),
	        ('2021.02.15','12005','PG',7),
	        ('2021.02.17','12006','PG',2),
	        ('2021.02.19','12007','PA',9),
	        ('2021.02.20','12001','PB',3),
	        ('2021.03.01','12005','PD',2),
	        ('2021.03.04','12006','PG',2),
	        ('2021.03.05','12010','PH',1),
	        ('2021.03.14','12010','PH',5),
	        ('2021.03.15','12003','PB',7);
	       
create view vw_all
as
select orderno ,date_format(date, "%Y.%m.%d") as date ,
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
	       
	       
select date_format(date, "%Y.%m.%d") from sale; 

select * from sale;
select * from product;
select * from customer;
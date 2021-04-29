select user(),database();


desc customer;
desc product;
desc sale;

insert into customer (cusno, passno, cusname, birth, callno, sex)
     values (12001,password(123),'홍길동','1977.04.05','01097415821',1),
     	    (12002,password(123),'김연수','1985.03.01','01045685581',2),
     	    (12003,password(123),'김지원','1986.07.08','01091115556',2),
     	    (12004,password(123),'문희원','1980.01.08','01077777777',2),
     	    (12005,password(123),'유일한','1979.02.05','01045668886',1),
     	    (12006,password(123),'김동수','1981.11.23','01012311211',1),
     	    (12007,password(123),'배진태','1982.07.07','01078777777',1),
     	    (12008,password(123),'류은수','1983.03.01','01074441474',2),
     	    (12009,password(123),'김동철','1987.04.26','01085251235',1),
     	    (12010,password(123),'최홍석','1990.04.05','01032146547',1);

select * from customer;

insert into product (procode, proname, proprice, stock, prodPic)
     values ('FU-1','책상',10000,100, '학교책상의자.png'),
     	    ('HA-1','냉장고',36000,40, '냉장고.png'),
     	    ('HA-2','세탁기',22000,80, '세탁기.png'),
     	    ('VE-1','킥보드',30000,100, '킥보드.png'),
     	    ('VE-2','자전거',90000,60, '자전거.png'),
     	    ('AC-1','시계',6000,10, '시계.png'),
     	    ('HA-3','TV',8000,50, 'TV.png'),
     	    ('HG-1','마스크',3000,100, 'mask.png');
     	    
select * from product;

insert into sale (date, cusno, procode, saleamount)
	 values ('2021.01.24','12003','FU-1',2),
	        ('2021.01.27','12001','FU-1',1),
	        ('2021.02.03','12009','AC-1',1),
	        ('2021.02.07','12010','HA-2',5),
	        ('2021.02.13','12003','HG-1',4),
	        ('2021.02.14','12002','HA-1',3),
	        ('2021.02.14','12004','HG-1',10),
	        ('2021.02.15','12005','AC-1',7),
	        ('2021.02.17','12006','VE-1',2),
	        ('2021.02.19','12007','HA-1',9),
	        ('2021.02.20','12001','VE-2',3),
	        ('2021.03.01','12005','VE-1',2),
	        ('2021.03.04','12006','HA-2',2),
	        ('2021.03.05','12010','VE-1',1),
	        ('2021.03.14','12010','HA-1',5),
	        ('2021.03.15','12003','VE-2',7);

	       
	     
insert into category (categoryCode, categoryName)
	 values ('FU','가구'),
	 		('AC','악세서리'),
	 		('HA','가전제품'),
	 		('HG','생활용품'),
	 		('VE','탈것');
	 
	 
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


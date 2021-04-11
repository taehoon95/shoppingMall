select user(),database();

desc customer;
desc product;
desc sale;

select * from customer;
select * from product;
select * from sale;

-- Main 화면 형식 sql문
create view vw_all
as;
select orderno ,date_format(date, "%Y.%m.%d") as date ,
	   c.cusNo,
	   c.passno,
	   c.cusName,
	   c.callNo,
	   p.procode,
	   p.proname,
	   p.proprice,
	   saleamount,
	   proPrice*saleamount*1.1 as sales,
	   p.proprice*saleamount*1.1 - p.proPrice*saleamount as profit 
  from sale s join product p on s.procode = p.procode join customer c on s.cusNo = c.cusNo
  order by date, c.cusNo;

drop view vw_all;

select * from vw_all va ;

select date,cusNo,cusName,callNo,procode,saleamount,sales from vw_all;

-- Main 화면에서 날짜별 조회
select * from vw_main where date = '2012.03.24';

-- 제품별 조회

select cusNo,`date`,procode,proName,saleamount,proprice,sales,profit from vw_all;

-- 상세 조회 
 
select orderno,date,procode,proName,cusName,saleamount,proPrice,sales,profit,cusno from vw_all;

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


select cusno,passno,cusname,birth,callno,sex from customer;

-- 제품코드 검색(제품별 조회에서)

select cusNo,date,procode,proName,saleamount,proprice,sales,profit from vw_all where procode = 'PA';

-- 회원번호 검색(상세 정보에서)

select orderno,date,procode,proName,cusName,saleamount,proPrice,sales,profit,cusno from vw_all where cusNo = 12001;

-- 회원번호 , 제품코드 둘다 검색(상세정보에서)
select orderno,date,procode,proName,cusName,saleamount,proPrice,sales,profit,cusno from vw_all where cusNo = 12001 and procode = 'PA';


-- 회원가입
insert into customer (cusno, passno, cusname, birth, callno, sex)
     values ('12011',password(123),'홍동','19990405','010-9741-5881',1);

select * from customer; 

delete  from customer where cusno = 12011;

-- 제품구입
insert into customer (cusno, passno, cusname, birth, callno, sex)
     values ('12011',password(123),'홍동','19990405','010-9741-5881',1);
insert into sale (date, cusno, procode, saleamount)
	 values ('2021.03.20','12011','PA',2);
	 
select * from vw_all;

-- 제품 환불
select * from customer;
delete from customer where cusno = '0';
select * from vw_all;


select cusno,passno,cusname,birth,callno,sex from customer;

delete from customer where cusno = 77777;


select cusno,passno from customer where cusno = '00000' and passno = password(123);


insert into customer (cusno, passno, cusname, birth, callno, sex)
     values ('00000',password(123),'이태훈','2999.01.01','010########',1);

select procode, proname, 1.1*proprice,stock as saleprice from product;


-- 제품구입
insert into customer (cusno, cusname, callno)
     values ('12011', '홍동','010-9741-5881');
insert into sale (date, cusno, procode, saleamount)
	 values ('2021.03.20','12011','PA',2);
	

select procode,proname,stock from product where procode = 'PA';

select procode,proname,proprice,stock,prodPic from product;





-- orderno 자동, date 자동
select `date`,cusNo,cusName,callNo,procode,proname,proprice,saleamount from vw_all where cusNo = 12001;

	
select orderno,`date`,cusNo, cusName,callNo,procode,proname,proprice,saleamount,sales,profit from vw_all;



-- 구매

insert into sale (date, cusno, procode, saleamount)
	 values ('2021.03.20','12008','PA',2);

update product set stock = stock - 2 where procode = "PA";


select * from vw_all va ;

select * from customer c ;

select * from product p ;



update product set stock = 10 where procode ='PF';

select cusno,cusname,birth,callno,sex from customer where cusno = 12001;

-- 판매내역 삭제

select * from sale;

delete from sale where orderno = ?;


-- detail정보 수정

select * from customer;
select * from product;
select * from sale;

























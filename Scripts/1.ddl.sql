-- 쇼핑몰
DROP SCHEMA IF EXISTS shopping;

-- 쇼핑몰
CREATE SCHEMA shopping;

-- 회원
CREATE TABLE shopping.customer (
	cusno   INT         NOT NULL COMMENT '회원번호', -- 회원번호
	passno  VARCHAR(41) NOT NULL COMMENT '비밀번호', -- 비밀번호
	cusname VARCHAR(20) NOT NULL COMMENT '회원이름', -- 회원이름
	birth   DATE        NULL     COMMENT '생년월일', -- 생년월일
	callno  VARCHAR(11) NULL     COMMENT '휴대전화번호', -- 휴대전화번호
	sex     INT         NULL     COMMENT '성별' -- 성별
)
COMMENT '회원';

-- 회원
ALTER TABLE shopping.customer
	ADD CONSTRAINT PK_customer -- 회원 기본키
		PRIMARY KEY (
			cusno -- 회원번호
		);

-- 제품정보
CREATE TABLE shopping.product (
	procode  CHAR(10)     NOT NULL COMMENT '제품코드', -- 제품코드
	proname  VARCHAR(200) NULL     COMMENT '제품명', -- 제품명
	proprice INT          NULL     COMMENT '제품단가', -- 제품단가
	stock    INT          NULL     COMMENT '재고', -- 재고
	prodPic  LONGBLOB     NULL     COMMENT '상품사진' -- 상품사진
)
COMMENT '제품정보';

-- 제품정보
ALTER TABLE shopping.product
	ADD CONSTRAINT PK_product -- 제품정보 기본키
		PRIMARY KEY (
			procode -- 제품코드
		);

-- 판매내역
CREATE TABLE shopping.sale (
	orderno    INT      NOT NULL primary key auto_increment COMMENT '주문번호', -- 주문번호
	date       DATE     NULL     COMMENT '주문날짜', -- 주문날짜
	cusno      INT      NOT NULL COMMENT '회원번호', -- 회원번호
	procode    CHAR(10) NOT NULL COMMENT '제품코드', -- 제품코드
	saleamount INT      NULL     COMMENT '판매수량' -- 판매수량
)
COMMENT '판매내역';


-- 회원관리
CREATE TABLE shopping.TABLE (
	cusID   VARCHAR(200) NOT NULL COMMENT 'ID', -- ID
	cusPass VARCHAR(200) NOT NULL COMMENT '비밀번호', -- 비밀번호
	cusno   INT          NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '회원관리';

-- 회원관리
ALTER TABLE shopping.TABLE
	ADD CONSTRAINT PK_TABLE -- 회원관리 기본키
		PRIMARY KEY (
			cusID -- ID
		);

-- 판매내역
ALTER TABLE shopping.sale
	ADD CONSTRAINT FK_customer_TO_sale -- 회원 -> 판매내역
		FOREIGN KEY (
			cusno -- 회원번호
		)
		REFERENCES shopping.customer ( -- 회원
			cusno -- 회원번호
		);

-- 판매내역
ALTER TABLE shopping.sale
	ADD CONSTRAINT FK_product_TO_sale -- 제품정보 -> 판매내역
		FOREIGN KEY (
			procode -- 제품코드
		)
		REFERENCES shopping.product ( -- 제품정보
			procode -- 제품코드
		);

-- 회원관리
ALTER TABLE shopping.TABLE
	ADD CONSTRAINT FK_customer_TO_TABLE -- 회원 -> 회원관리
		FOREIGN KEY (
			cusno -- 회원번호
		)
		REFERENCES shopping.customer ( -- 회원
			cusno -- 회원번호
		);